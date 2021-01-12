package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Category;
import cn.cy.course.pojo.vo.CategoryCountVo;

import java.util.*;

/**
 * category业务逻辑层
 */
public interface CategoryService {


    public List<Category> findAll();


    public PageResult<Category> findPage(int page, int size);


    public List<Category> findList(Map<String, Object> searchMap);


    public PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size);


    public Category findById(Integer id);

    public void add(Category category);


    public void update(Category category);


    public void delete(Integer id);

    CategoryCountVo countCategory();
}
