package cn.cy.course.mapper;

import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
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
    @Select("SELECT c.id,c.`name`,c.term,c.credit,c.time,c.duration_start,c.duration_end,c.place,c.`online`,c.teacher_name,c.category_id,c.total FROM tb_selection s LEFT JOIN tb_course c ON s.course_id=c.id WHERE s.student_id=#{studentId} AND s.term < #{term}")
    public List<Course> historyTerm(@Param("term") Integer term, @Param("studentId") String studentId);
}
