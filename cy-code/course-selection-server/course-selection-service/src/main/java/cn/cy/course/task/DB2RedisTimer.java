package cn.cy.course.task;

import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 将MySQL中的数据更新到Redis中
 *
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/18 10:30 下午
 */
@Component
public class DB2RedisTimer {

    @Reference
    private CourseService courseService;

    @Autowired
    private RedisTemplate redisTemplate;

    private String COURSE_MSG_HASH = "COURSE_MSG_HASH";

    private String COURSE_STOCK_QUEUE = "COURSE_STOCK_QUEUE";

    private String COURSE_STOCK_HASH = "COURSE_STOCK_HASH";

    /**
     * 将课程信息更新到Redis中
     */
    public void Course2Redis() {
        List<Course> courseList = courseService.findAll();
        for (Course course : courseList) {
            String courseId = course.getId();
            // 将课程信息存放到hash中
            redisTemplate.boundHashOps(COURSE_MSG_HASH).put(courseId, course);


            // 初始化库存队列
            redisTemplate.delete(COURSE_STOCK_QUEUE + course.getId());
            for (int i = 0; i < course.getCount(); i++) {
                redisTemplate.boundListOps(COURSE_STOCK_QUEUE + course.getId()).leftPush(courseId);
                redisTemplate.boundHashOps(COURSE_STOCK_HASH).put(courseId, course.getCount());
            }
        }
    }

}
