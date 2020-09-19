package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.StudentMapper;
import cn.cy.course.pojo.Student;
import cn.cy.course.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service(interfaceClass = StudentService.class)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Student> findAll() {
        return studentMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Student> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Student> students = (Page<Student>) studentMapper.selectAll();
        return new PageResult<Student>(students.getTotal(),students.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Student> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return studentMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Student> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Student> students = (Page<Student>) studentMapper.selectByExample(example);
        return new PageResult<Student>(students.getTotal(),students.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Student findById(String id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param student
     */
    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    /**
     * 修改
     * @param student
     */
    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(String id) {
        studentMapper.deleteByPrimaryKey(id);
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Student.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 学生表ID，学号
            if(searchMap.get("id")!=null && !"".equals(searchMap.get("id"))){
                criteria.andLike("id","%"+searchMap.get("id")+"%");
            }
            // 学生姓名
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }
            // 密码
            if(searchMap.get("password")!=null && !"".equals(searchMap.get("password"))){
                criteria.andLike("password","%"+searchMap.get("password")+"%");
            }
            // 居民身份证
            if(searchMap.get("idNumber")!=null && !"".equals(searchMap.get("idNumber"))){
                criteria.andLike("idNumber","%"+searchMap.get("idNumber")+"%");
            }
            // 学院/系
            if(searchMap.get("college")!=null && !"".equals(searchMap.get("college"))){
                criteria.andLike("college","%"+searchMap.get("college")+"%");
            }
            // 专业
            if(searchMap.get("major")!=null && !"".equals(searchMap.get("major"))){
                criteria.andLike("major","%"+searchMap.get("major")+"%");
            }
            // 专业班级
            if(searchMap.get("class")!=null && !"".equals(searchMap.get("class"))){
                criteria.andLike("class","%"+searchMap.get("class")+"%");
            }

            // 0:女 1:男
            if(searchMap.get("sex")!=null ){
                criteria.andEqualTo("sex",searchMap.get("sex"));
            }

        }
        return example;
    }

}

