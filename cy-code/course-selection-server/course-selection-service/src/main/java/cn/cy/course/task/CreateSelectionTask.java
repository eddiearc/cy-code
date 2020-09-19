package cn.cy.course.task;

import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Pack;
import cn.cy.course.pojo.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/18 5:10 下午
 */
@Component
public class CreateSelectionTask {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private SelectionMapper selectionMapper;

    private String SECKILL_QUEUE = "SECKILL_QUEUE";

    /**
     * 从redis list中取出处理
     * 1. Redis课程剩余数量-1
     * 2. 将处理好的抢课信息入到MQ延时队列中，等待入库
     * 3. Web-Socket回调前端提示抢课成功
     */
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void createSelection() {
        Pack pack = (Pack) redisTemplate.boundListOps(SECKILL_QUEUE).rightPop();
        Set<String> courseIdSet = pack.getCourseIdSet();

        for (String courseId : courseIdSet) {
            Selection selection = new Selection();
            selection.setCourseId(courseId);
            selection.setStudentId(pack.getStudentId());
            selection.setTerm(pack.getTerm());
        }


    }
}
