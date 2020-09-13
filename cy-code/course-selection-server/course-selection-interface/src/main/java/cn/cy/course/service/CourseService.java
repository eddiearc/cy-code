package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * course业务逻辑层
 */
public interface CourseService {


    public List<Course> findAll();


    public PageResult<Course> findPage(int page, int size);


    public List<Course> findList(Map<String, Object> searchMap);


    public PageResult<Course> findPage(Map<String, Object> searchMap, int page, int size);


    public Course findById(String id);


    public void add(Course course);


    public void update(Course course);


    public void delete(String id);
}
