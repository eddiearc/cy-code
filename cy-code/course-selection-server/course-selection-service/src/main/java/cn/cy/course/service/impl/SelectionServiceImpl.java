package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.SelectionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    private String SELECTION_STU = "SELECTION_STU";

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
        List<Selection> list = (List<Selection>) redisTemplate.boundValueOps(SELECTION_STU + studentId).get();

        // redis中没有数据
        if (list == null) {
            Selection selection = new Selection();
            selection.setCourseId(studentId);
            list = selectionMapper.select(selection);
            // 存到redis中 30分钟后过期
            redisTemplate.boundValueOps(SELECTION_STU + studentId).set(list, 30, TimeUnit.MINUTES);
        }

        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Selection selection) {
        // 使对应的redis中的学生选课信息过期
        redisTemplate.delete(SELECTION_STU + selection.getStudentId());
        selectionMapper.insertSelective(selection);
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

        // 使对应的redis中的学生选课信息过期
        redisTemplate.delete(SELECTION_STU + selection.getStudentId());

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
        // 使对应的redis中的学生选课信息过期
        redisTemplate.delete(SELECTION_STU + studentId);

        Selection selection = new Selection();
        selectionMapper.delete(selection);
    }
}
