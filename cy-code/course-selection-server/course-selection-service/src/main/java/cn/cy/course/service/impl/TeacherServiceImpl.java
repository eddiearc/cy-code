package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.TeacherMapper;
import cn.cy.course.pojo.Teacher;
import cn.cy.course.service.TeacherService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = TeacherService.class)
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Teacher> findAll() {
        return teacherMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Teacher> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Teacher> teachers = (Page<Teacher>) teacherMapper.selectAll();
        return new PageResult<Teacher>(teachers.getTotal(),teachers.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Teacher> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return teacherMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Teacher> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Teacher> teachers = (Page<Teacher>) teacherMapper.selectByExample(example);
        return new PageResult<Teacher>(teachers.getTotal(),teachers.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Teacher findById(String id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param teacher
     */
    @Override
    public void add(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    /**
     * 修改
     * @param teacher
     */
    @Override
    public void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Teacher.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 教师表ID
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 教师名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }


        }
        return example;
    }

}

