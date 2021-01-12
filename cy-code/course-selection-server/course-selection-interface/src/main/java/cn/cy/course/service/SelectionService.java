package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;

import java.util.*;

/**
 * selection业务逻辑层
 */
public interface SelectionService {

    /**
     * 找出所有的选课情况
     *
     * @return
     */
    public List<Selection> findAll();


    /**
     * 找出所有的选课情况并分页
     *
     * @param page
     * @param size
     * @return
     */
    public PageResult<Selection> findPage(int page, int size);


    /**
     *  根据条件找出选课情况
     *
     * @param searchMap
     * @return
     */
    public List<Selection> findList(Map<String, Object> searchMap);


    /**
     * 根据条件找出选课情况并分页
     *
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public PageResult<Selection> findPage(Map<String, Object> searchMap, int page, int size);


    /**
     * 根据课程ID查找选课信息
     *
     * @param courseId
     * @return
     */
    public List<Selection> findByCourseId(String courseId);


    /**
     * 根据学号查找选课信息
     *
     * @param studentId
     * @return
     */
    public List<Selection> findByStudentId(String studentId);


    /**
     * 需指明studentId 与 courseId
     */
    public int add(Selection selection);


    /**
     * 需指明studentId 与 courseId
     *
     * @param selection 课程选课情况
     */
    public void update(Selection selection);


    /**
     * 根据studentId 与 courseId删除选课信息
     *
     * @param studentId
     * @param courseId
     */
    public void delete(String studentId, String courseId);


    /**
     * 根据studentId查询出历史的term
     *
     * @param studentId
     * @return
     */
    public List<Course> historySelection(String studentId);

    /**
     * 根据studentId查询历史的term
     *
     * @param studentId
     * @return id -> Course
     */
    public Map<String, Course> currentTermSelection(String studentId);

}
