package cn.cy.course.mapper;

import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/13 11:30 上午
 */
public interface SelectionMapper extends Mapper<Selection> {

    /**
     * 返回当前学期term值
     *
     * @return
     */
    @Select("SELECT MAX(term) currTerm FROM tb_curr_term")
    public Integer currTerm();

    /**
     * 查询历史的选课记录
     *
     * @return
     */
    @Select("SELECT ")
    public List<Course> historyTerm(@Param("term") Integer term);
}
