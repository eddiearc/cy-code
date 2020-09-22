package cn.cy.course.mapper;

import cn.cy.course.pojo.Selection;
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
     *
     * @return
     */
    @Select("SELECT student_id, course_id FROM tb_selection GROUP BY student_id, course_id")
    public List<Selection> selectGroupByStudentId();
}
