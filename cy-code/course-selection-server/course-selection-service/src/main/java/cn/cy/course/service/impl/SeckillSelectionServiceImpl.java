package cn.cy.course.service.impl;

import cn.cy.course.pojo.Pack;
import cn.cy.course.service.SeckillSelectionService;
import cn.cy.course.task.CreateSelectionExcutor;
import cn.cy.course.util.RedisConstantKey;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:36 下午
 */
@Service(interfaceClass = SeckillSelectionService.class)
public class SeckillSelectionServiceImpl implements SeckillSelectionService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CreateSelectionExcutor createSelectionExcutor;


    @Override
    public void add(Pack pack) {
        //1. 检查课程库存 减少无效排队
        String courseId = pack.getCourseId();
        Long stockCount = redisTemplate.boundListOps(RedisConstantKey.COURSE_STOCK_QUEUE + courseId).size();
        if (stockCount == null || stockCount <= 0) {
            throw new RuntimeException("该课程已经被选完了!");
        }

        //2. 检查用户是否有重复的选课， Redis - set
        Boolean isSelected = redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET + pack.getStudentId()).isMember(pack.getCourseId());
        if (isSelected != null && isSelected) {
            throw new RuntimeException("您已经选过该课程！");
        }

        //3. 入队列
        redisTemplate.boundListOps(RedisConstantKey.SECKILL_QUEUE).leftPush(pack);

        //4. 异步处理任务Pack
        createSelectionExcutor.createSelection();
    }
}
