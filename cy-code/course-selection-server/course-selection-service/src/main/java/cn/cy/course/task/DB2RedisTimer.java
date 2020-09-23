package cn.cy.course.task;

import cn.cy.course.mapper.CourseMapper;
import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
@EnableScheduling
public class DB2RedisTimer {

    @Autowired(required = false)
    private CourseMapper courseMapper;

    @Autowired(required = false)
    private SelectionMapper selectionMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private String COURSE_MSG_HASH = "COURSE_MSG_HASH";

    private String COURSE_STOCK_QUEUE = "COURSE_STOCK_QUEUE";

    private String COURSE_STOCK_HASH = "COURSE_STOCK_HASH";

    private String SELECTION_SET = "SELECTION_SET";

    private String CURR_TERM = "CURR_TERM";

    /**
     * 初始化term值
     */
    public void initTerm() {
        redisTemplate.boundValueOps(CURR_TERM).set(selectionMapper.currTerm());
    }

    /**
     * 将课程信息更新到Redis中
     */
    @PostConstruct
    public void course2Redis() {
        Course searchCourse = new Course();
        Integer term = (Integer) redisTemplate.boundValueOps(CURR_TERM).get();
        if (term == null) {
            initTerm();
        }
        searchCourse.setTerm(term);
        final List<Course> courseList = courseMapper.select(searchCourse);
        // delete msg
        redisTemplate.delete(COURSE_STOCK_HASH);
        // 将所有的Course信息存入Redis
        for (Course course : courseList) {
            String courseId = course.getId();
            // 将课程信息存放到hash中
            redisTemplate.boundHashOps(COURSE_MSG_HASH).put(courseId, course);

            // 初始化库存队列
            redisTemplate.delete(COURSE_STOCK_QUEUE + course.getId());
            for (int i = 0; i < course.getStock(); i++) {
                redisTemplate.boundListOps(COURSE_STOCK_QUEUE + course.getId()).leftPush(courseId);
            }
            redisTemplate.boundHashOps(COURSE_STOCK_HASH).increment(courseId, course.getStock());
        }
    }

    /**
     * 将每个用户对应的选课信息以Set形式存到Redis中
     */
    @PostConstruct
    public void selection2Redis() {
        Selection searchSelection = new Selection();
        Integer term = (Integer) redisTemplate.boundValueOps(CURR_TERM).get();
        if (term == null) {
            initTerm();
        }
        final List<Selection> list = selectionMapper.select(searchSelection);

        // set
        for (Selection selection : list) {
            String currStuId = selection.getStudentId();
            redisTemplate.boundSetOps(SELECTION_SET + currStuId).add(selection.getCourseId());
        }
    }
}
