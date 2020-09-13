package cn.cy.course.service;

import cn.cy.course.entity.PageResult;
import cn.cy.course.pojo.Selection;

import java.util.*;

/**
 * selection业务逻辑层
 */
public interface SelectionService {


    public List<Selection> findAll();


    public PageResult<Selection> findPage(int page, int size);


    public List<Selection> findList(Map<String, Object> searchMap);


    public PageResult<Selection> findPage(Map<String, Object> searchMap, int page, int size);


    public Selection findById(String id);

    public void add(Selection selection);


    public void update(Selection selection);


    public void delete(String id);

}
