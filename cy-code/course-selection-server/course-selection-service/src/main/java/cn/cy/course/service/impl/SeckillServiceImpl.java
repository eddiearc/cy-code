package cn.cy.course.service.impl;

import cn.cy.course.pojo.Pack;
import cn.cy.course.service.SeckillService;
import cn.cy.course.service.SelectionService;
import cn.cy.course.task.CreateSelectionExecutor;
import cn.cy.course.util.RedisConstantKey;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:36 下午
 */
@Service(interfaceClass = SeckillService.class)
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CreateSelectionExecutor createSelectionExecutor;

    @Autowired
    private SelectionService selectionService;


    @Override
    public void add(Pack pack) {
        //1. 检查课程库存 减少无效排队
        String courseId = pack.getCourseId();
        Long stockCount = redisTemplate.boundListOps(RedisConstantKey.COURSE_STOCK_QUEUE.toString() + courseId).size();
        if (stockCount == null || stockCount <= 0) {
            throw new RuntimeException("该课程已经被选完了!");
        }

        //2. 检查用户是否有重复的选课， Redis - set
        Boolean isSelected = redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + pack.getStudentId()).isMember(pack.getCourseId());
        if (isSelected != null && isSelected) {
            throw new RuntimeException("您已经选过该课程！");
        }

        //3. 入队列
        redisTemplate.boundListOps(RedisConstantKey.SECKILL_QUEUE.toString()).leftPush(pack);

        //4. 异步处理任务Pack
        createSelectionExecutor.createSelection();
    }

    @Override
    public void remove(Pack pack) {
        System.out.println(pack.getCourseId() + " " + pack.getStudentId());
        Boolean included = redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + pack.getStudentId()).isMember(pack.getCourseId());
        if (included == null || !included) {
            throw new RuntimeException("该选课记录已经被移除！");
        }

        //1. mysql移除选课纪录
        selectionService.delete(pack.getStudentId(), pack.getCourseId());

        //2. redis选课记录移除
        redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + pack.getStudentId()).remove(pack.getCourseId());

        //3. redis课程库存回滚
        createSelectionExecutor.redisStockRollBack(pack.getCourseId());
    }
}
