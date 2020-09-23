package cn.cy.course.service.impl;
import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.CourseMapper;
import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import cn.cy.course.util.RedisConstantKey;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = CourseService.class)
public class CourseServiceImpl implements CourseService {

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private CourseMapper courseMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Course> findAll() {
        return courseMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Course> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Course> courses = (Page<Course>) courseMapper.selectAll();
        return new PageResult<Course>(courses.getTotal(),courses.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Course> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return courseMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Course> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Course> courses = (Page<Course>) courseMapper.selectByExample(example);
        return new PageResult<Course>(courses.getTotal(),courses.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Course findById(String id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param course
     */
    @Override
    public void add(Course course) {
        courseMapper.insert(course);
    }

    /**
     * 修改
     * @param course
     */
    @Override
    public void update(Course course) {
        redisTemplate.boundHashOps(RedisConstantKey.COURSE_MSG_HASH.toString()).delete(course.getId());
        courseMapper.updateByPrimaryKeySelective(course);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(String id) {
        courseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Course.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 选修的班级号
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 选修课名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 上课具体时间
            if(searchMap.get("time")!=null && !"".equals(searchMap.get("time"))){
                criteria.andLike("time","%"+searchMap.get("time")+"%");
            }
            // 上课地点
            if(searchMap.get("place")!=null && !"".equals(searchMap.get("place"))){
                criteria.andLike("place","%"+searchMap.get("place")+"%");
            }
            // 上课老师的工号
            if(searchMap.get("teacherId")!=null && !"".equals(searchMap.get("teacherId"))){
                criteria.andLike("teacherId","%"+searchMap.get("teacherId")+"%");
            }
            // 上课老师的姓名，冗余字段
            if(searchMap.get("teacherName")!=null && !"".equals(searchMap.get("teacherName"))){
                criteria.andLike("teacherName","%"+searchMap.get("teacherName")+"%");
            }

            // 学期标识
            if(searchMap.get("term")!=null ){
                criteria.andEqualTo("term",searchMap.get("term"));
            }
            // 学分
            if(searchMap.get("credit")!=null ){
                criteria.andEqualTo("credit",searchMap.get("credit"));
            }
            // 开始的周数
            if(searchMap.get("durationStart")!=null ){
                criteria.andEqualTo("durationStart",searchMap.get("durationStart"));
            }
            // 结束的周数
            if(searchMap.get("durationEnd")!=null ){
                criteria.andEqualTo("durationEnd",searchMap.get("durationEnd"));
            }
            // 是否是网课，0:是， 1:不是
            if(searchMap.get("online")!=null ){
                criteria.andEqualTo("online",searchMap.get("online"));
            }
            // 课程类别Id
            if(searchMap.get("categoryId")!=null ){
                criteria.andEqualTo("categoryId",searchMap.get("categoryId"));
            }
            // 课程数
            if(searchMap.get("count")!=null ){
                criteria.andEqualTo("count",searchMap.get("count"));
            }

        }
        return example;
    }

}

