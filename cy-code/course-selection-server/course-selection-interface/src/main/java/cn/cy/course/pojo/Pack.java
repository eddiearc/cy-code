package cn.cy.course.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/18 4:22 下午
 */
public class Pack implements Serializable {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 选课id，一次请求只能有一个
     */
    private String courseId;

    /**
     * 第几学期
     */
    private Integer term;

    /**
     * 选课的时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "Pack{" +
                "studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", term=" + term +
                ", createTime=" + createTime +
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
