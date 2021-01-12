package cn.cy.course.task;

import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Pack;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.CourseService;
import cn.cy.course.service.SelectionService;
import cn.cy.course.util.RedisConstantKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/18 5:10 下午
 */
@Component
public class CreateSelectionExcutor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private SelectionService selectionService;

    @Autowired(required = false)
    private CourseService courseService;

    /**
     * 1. 从redis list中取出处理
     * 2. 处理库存（库存队列，课程信息库存）
     * 3. 将处理好的抢课信息入到MQ延时队列中，等待入库
     */
    @Async
    public void createSelection() {
        System.out.println("-----抢课Pack处理-START-----");
        // 1. 取出任务
        Pack pack = (Pack) redisTemplate.boundListOps(RedisConstantKey.SECKILL_QUEUE.toString()).rightPop();
        String courseId = pack.getCourseId();

        // 2. 消去库存，并验证是否已经被抢完了
        String queueId = (String) redisTemplate.boundListOps(RedisConstantKey.COURSE_STOCK_QUEUE.toString() + courseId).rightPop();
        if (queueId == null || !courseId.equals(queueId)) {
            // 3. 提示没抢成功

            //4. 修改课程数据库信息与redis中的信息
            updateStock(courseId, 0);
            System.out.println("-----抢课Pack处理-OVER-----");
            return;
        }

        /**
         * 3. 更新课程信息中的库存
         *
         * 从Course-stock-hash中取出库存数量
         * 借助Redis的单线程原子性操作进行库存自减
         *
         * 防止多线程问题出现数据不一致问题
          */
        Long stock = redisTemplate.boundHashOps(RedisConstantKey.COURSE_STOCK_HASH.toString()).increment(courseId, -1);
        System.out.println("剩余库存：" + stock);

        // 4. 选课信息入Redis
        redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + pack.getStudentId()).add(pack.getCourseId());


        // 5. 选课信息入MySQL
        Selection selection = new Selection();
        selection.setStudentId(pack.getStudentId());
        selection.setCourseId(courseId);
        selection.setCreateTime(pack.getCreateTime());
        try {
            selectionService.add(selection);
        } catch (DuplicateKeyException e) {
            // 插入异常，进行回滚操作，即已经选过该课程
            System.out.println("---学号：" + pack.getStudentId() + "已经选过该课了---");
            redisStockRollBack(courseId);
        } catch (Exception e) {
            System.out.println("预料之外的错误：" + e);
        }


        System.out.println("-----抢课Pack处理-OVER-----");
    }

    /**
     * 更新数据库中的，课程库存信息
     *
     * @param courseId
     * @param stock
     */
    private void updateStock(String courseId, int stock) {
        Course course = new Course();
        course.setId(courseId);
        course.setStock(stock);
        // DB update
        courseService.update(course);
    }

    /**
     * 已经选过该课程，回滚Redis中的库存
     *
     * @param courseId 课程ID
     */
    public void redisStockRollBack(String courseId) {
        // 库存数回滚
        redisTemplate.boundListOps(RedisConstantKey.COURSE_STOCK_QUEUE.toString()).leftPush(courseId);
        redisTemplate.boundHashOps(RedisConstantKey.COURSE_STOCK_HASH.toString()).increment(courseId, 1);
    }
}
