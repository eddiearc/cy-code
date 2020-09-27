package cn.cy.course.task;

import cn.cy.course.mapper.CourseMapper;
import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import cn.cy.course.util.RedisConstantKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

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

    /**
     * 初始化term值
     */
    public void initTerm() {
        redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).set(selectionMapper.currTerm());
    }

    /**
     * 将课程信息更新到Redis中
     */
    @PostConstruct
    public void course2Redis() {
        Course searchCourse = new Course();
        Integer term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        if (term == null) {
            initTerm();
        }
        searchCourse.setTerm(term);
        final List<Course> courseList = courseMapper.select(searchCourse);
        // 先根据id长度排序，再根据id的字符串排列排序
        Collections.sort(courseList, Comparator.comparingInt((Course o) -> o.getId().length()).thenComparing(Course::getId));
        // delete msg
        redisTemplate.delete(RedisConstantKey.COURSE_STOCK_HASH.toString());
        // 将所有的Course信息存入Redis
        List<String> ids = new ArrayList<>(courseList.size());
        for (Course course : courseList) {
            String courseId = course.getId();
            ids.add(courseId);
            // 将课程信息存放到hash中
            redisTemplate.boundHashOps(RedisConstantKey.COURSE_MSG_HASH.toString()).put(courseId, course);

            // 初始化库存队列
            redisTemplate.delete(RedisConstantKey.COURSE_STOCK_QUEUE.toString() + course.getId());
            for (int i = 0; i < course.getStock(); i++) {
                redisTemplate.boundListOps(RedisConstantKey.COURSE_STOCK_QUEUE.toString() + course.getId()).leftPush(courseId);
            }
            redisTemplate.boundHashOps(RedisConstantKey.COURSE_STOCK_HASH.toString()).increment(courseId, course.getStock());
        }
        // IDS
        redisTemplate.boundValueOps(RedisConstantKey.COURSE_IDS.toString()).set(ids);
    }

    /**
     * 将每个用户对应的选课信息以Set形式存到Redis中
     */
    @PostConstruct
    public void selection2Redis() {
        Selection searchSelection = new Selection();
        Integer term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        if (term == null) {
            initTerm();
        }
        final List<Selection> list = selectionMapper.select(searchSelection);

        // 先清空Set
        Set keys = redisTemplate.keys(RedisConstantKey.SELECTION_SET.toString() + "*");
        redisTemplate.delete(keys);

        // set
        for (Selection selection : list) {
            String currStuId = selection.getStudentId();
            redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + currStuId).add(selection.getCourseId());
        }
    }
}
