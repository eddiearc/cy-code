package cn.cy.course.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 8:58 下午
 */
@Table(name = "tb_selection")
public class Selection implements Serializable {

    /**
     * 学生ID
     */
    @Id
    private String studentId;

    /**
     * 课程ID
     */
    @Id
    private String courseId;

    /**
     * 第几个学期，2019-2020下学期的编号为0，后面的学期递增
     */
    private Integer term;

    /**
     * 选课阶段
     */
    private Integer stage;

    @Override
    public String toString() {
        return "Selection{" +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", term=" + term +
                ", stage=" + stage +
                '}';
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }
}
