package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Student;

import java.util.*;

/**
 * student业务逻辑层
 */
public interface StudentService {


    public List<Student> findAll();


    public PageResult<Student> findPage(int page, int size);


    public List<Student> findList(Map<String, Object> searchMap);


    public PageResult<Student> findPage(Map<String, Object> searchMap, int page, int size);


    public Student findById(String id);

    public void add(Student student);


    public void update(Student student);


    public void delete(String id);

}
