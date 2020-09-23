package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.SelectionService;
import cn.cy.course.task.DB2RedisTimer;
import cn.cy.course.util.RedisConstantKey;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 4:10 下午
 */
@Service(interfaceClass = SelectionService.class)
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private SelectionMapper selectionMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List<Selection> findAll() {
        return selectionMapper.selectAll();
    }

    @Override
    public PageResult<Selection> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<Selection> selections = (Page<Selection>) selectionMapper.selectAll();
        return new PageResult<>(selections.getTotal(), selections.getResult());
    }

    @Override
    public List<Selection> findList(Map<String, Object> searchMap) {
        Example example = new Example(Selection.class);
        Example.Criteria criteria = example.createCriteria();
        for (Map.Entry<String, Object> entry : searchMap.entrySet()) {
            switch (entry.getKey()) {
//                case "studentId":
//                    criteria.andEqualTo("student_id", entry.getValue());
//                    break;
//                case "courseId":
//                    criteria.andEqualTo("course_id", entry.getValue());
//                    break;
                default:
                    criteria.andEqualTo(entry.getKey(), entry.getValue());
            }
        }
        return selectionMapper.selectByExample(example);
    }

    @Override
    public PageResult<Selection> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Page<Selection> selections = (Page<Selection>) this.findList(searchMap);
        return new PageResult<Selection>(selections.getTotal(), selections.getResult());
    }

    /**
     * 根据课程ID查找选课信息
     *
     * @param courseId
     * @return
     */
    @Override
    public List<Selection> findByCourseId(String courseId) {
        Selection selection = new Selection();
        selection.setCourseId(courseId);
        return selectionMapper.select(selection);
    }

    /**
     * 根据学号查找选课信息
     *
     * @param studentId
     * @return
     */
    @Override
    @Transactional
    public List<Selection> findByStudentId(String studentId) {
        Selection selection = new Selection();
        selection.setStudentId(studentId);
        return selectionMapper.select(selection);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(Selection selection) {
        return selectionMapper.insertSelective(selection);
    }

    /**
     * 需指明studentId 与 courseId
     *
     * @param selection 课程选课情况
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Selection selection) {
        // 合法判断
        if (selection.getStudentId() == null || selection.getCourseId() == null ||
        "".equals(selection.getStudentId()) || "".equals(selection.getCourseId())) {
            throw new RuntimeException("需指明studentId与courseId!");
        }

        // 更新条件
        Example example = new Example(Selection.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("studentId", selection.getStudentId());
        criteria.andEqualTo("courseId", selection.getCourseId());
        selectionMapper.updateByExampleSelective(selection, example);
    }

    /**
     * 需指明studentId 与 courseId
     *
     * @param studentId
     * @param courseId
     */
    @Override
    public void delete(String studentId, String courseId) {
        Selection selection = new Selection();
        selectionMapper.delete(selection);
    }

    @Autowired
    private DB2RedisTimer db2RedisTimer;

    @Override
    public List<Course> historySelection(String studentId) {
        // redis中存在该数据
        List<Course> list = (List<Course>) redisTemplate.boundHashOps(RedisConstantKey.SELECTION_HISTORY_HASH.toString()).get(studentId);
        if (list != null) {
            return list;
        }
        // 获取本学期标记数
        Integer term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        if (term == null) {
            db2RedisTimer.initTerm();
            term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        }

        // 除了当前学期其余都加载
        list = selectionMapper.historyTerm(term, studentId);
        // 存储到Redis中
        redisTemplate.boundHashOps(RedisConstantKey.SELECTION_HISTORY_HASH.toString()).put(studentId, list);
        return list;
    }

    /**
     * 选课期间获取当前学期的选课情况，选课信息实时更新
     *
     * @param studentId
     * @return
     */
    @Override
    public List<Course> currTermSelection(String studentId) {
        // 获取本学期标记数
        Integer term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        if (term == null) {
            db2RedisTimer.initTerm();
            term = (Integer) redisTemplate.boundValueOps(RedisConstantKey.CURR_TERM.toString()).get();
        }
        Set<String> ids = redisTemplate.boundSetOps(RedisConstantKey.SELECTION_SET.toString() + studentId).members();
        List<Course> ans = new ArrayList<>(ids.size());

        for (String courseId : ids) {
            // redis中的课程信息
            Course course = (Course) redisTemplate.boundHashOps(RedisConstantKey.COURSE_MSG_HASH.toString()).get(courseId);
            ans.add(course);
        }
        return ans;
    }
}
