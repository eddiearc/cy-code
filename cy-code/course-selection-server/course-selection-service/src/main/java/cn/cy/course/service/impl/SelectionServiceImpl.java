package cn.cy.course.service.impl;

import cn.cy.course.entity.PageResult;
import cn.cy.course.mapper.SelectionMapper;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.SelectionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 4:10 下午
 */
@Service(interfaceClass = SelectionService.class)
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private SelectionMapper selectionMapper;

    @Override
    public List<Selection> findAll() {
        return selectionMapper.selectAll();
    }

    @Override
    public PageResult<Selection> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        Page<Selection> selections = (Page<Selection>) selectionMapper.selectAll();
        return new PageResult<>(selections.getTotal(), selections.getResult());
    }

    @Override
    public List<Selection> findList(Map<String, Object> searchMap) {
        Example example = new Example(Selection.class);
        
        return selectionMapper.selectByExample(example);
    }

    @Override
    public PageResult<Selection> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        return (PageResult<Selection>) this.findList(searchMap);
    }

    @Override
    public Selection findById(String id) {
        return null;
    }

    @Override
    public void add(Selection selection) {

    }

    @Override
    public void update(Selection selection) {

    }

    @Override
    public void delete(String id) {

    }
}
