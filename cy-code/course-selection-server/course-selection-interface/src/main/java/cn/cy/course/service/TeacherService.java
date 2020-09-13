package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Teacher;

import java.util.*;

/**
 * teacher业务逻辑层
 */
public interface TeacherService {


    public List<Teacher> findAll();


    public PageResult<Teacher> findPage(int page, int size);


    public List<Teacher> findList(Map<String, Object> searchMap);


    public PageResult<Teacher> findPage(Map<String, Object> searchMap, int page, int size);


    public Teacher findById(String id);

    public void add(Teacher teacher);


    public void update(Teacher teacher);


    public void delete(String id);

}
