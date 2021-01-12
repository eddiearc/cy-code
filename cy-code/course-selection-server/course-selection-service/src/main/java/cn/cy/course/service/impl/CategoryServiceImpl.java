package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.CategoryMapper;
import cn.cy.course.pojo.Category;
import cn.cy.course.pojo.dto.CategoryIdCountDto;
import cn.cy.course.service.CategoryService;
import cn.cy.course.service.CourseService;
import cn.cy.course.pojo.vo.CategoryCountVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CategoryService.class)
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CourseService courseService;

    /**
     * 返回全部记录
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectAll();
    }

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页记录数
     * @return 分页结果
     */
    @Override
    public PageResult<Category> findPage(int page, int size) {
        PageHelper.startPage(page,size);
        Page<Category> categorys = (Page<Category>) categoryMapper.selectAll();
        return new PageResult<Category>(categorys.getTotal(),categorys.getResult());
    }

    /**
     * 条件查询
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Category> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return categoryMapper.selectByExample(example);
    }

    /**
     * 分页+条件查询
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Category> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page,size);
        Example example = createExample(searchMap);
        Page<Category> categorys = (Page<Category>) categoryMapper.selectByExample(example);
        return new PageResult<Category>(categorys.getTotal(),categorys.getResult());
    }

    /**
     * 根据Id查询
     * @param id
     * @return
     */
    @Override
    public Category findById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增
     * @param category
     */
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 修改
     * @param category
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     *  删除
     * @param id
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 统计课程分类课程数量用于图表显示
     *
     * @return
     */
    @Override
    public CategoryCountVo countCategory() {
        List<Category> categories = categoryMapper.selectAll();
        // 将所有的课程分类转化为ID -> Category 的K-V 键值对
        Map<Integer, Category> idCategoryMap = listToMap(categories);

        CategoryCountVo ccv = new CategoryCountVo(categories.size());
        List<CategoryIdCountDto> categoryIdCountDtos = categoryMapper.countPeople();

        Collections.sort(categoryIdCountDtos, (a, b) -> b.getCount() - a.getCount());

        for (CategoryIdCountDto categoryIdCountDto : categoryIdCountDtos) {
            String name = idCategoryMap.get(categoryIdCountDto.getId()).getName();
            ccv.add(name, categoryIdCountDto.getCount());
        }

        return ccv;
    }

    private Map<Integer, Category> listToMap(List<Category> categories) {
        Map<Integer, Category> res = new HashMap<>();
        for (Category category : categories) {
            res.put(category.getId(), category);
        }
        return res;
    }

    /**
     * 构建查询条件
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String, Object> searchMap){
        Example example=new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 课程类别名称
            if(searchMap.get("name")!=null && !"".equals(searchMap.get("name"))){
                criteria.andLike("name","%"+searchMap.get("name")+"%");
            }

            // 分类ID
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

}

