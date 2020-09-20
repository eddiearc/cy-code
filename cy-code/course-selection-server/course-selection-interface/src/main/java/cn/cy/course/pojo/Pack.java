package cn.cy.course.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/18 4:22 下午
 */
public class Pack implements Serializable {

    /**
     * 学号
     */
    private String studentId;

    /**
     * 选课包,存放courseID，根据Set不重复
     */
    private Set<String> courseIdSet;

    /**
     * 第几学期
     */
    private Integer term;

    public Pack() {
        this.courseIdSet = new HashSet<>(2);
    }

    @Override
    public String toString() {
        return "Pack{" +
                "studentId='" + studentId + '\'' +
                ", courseIdSet=" + courseIdSet +
                ", term=" + term +
                '}';
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Set<String> getCourseIdSet() {
        return courseIdSet;
    }

    public void setCourseIdSet(Set<String> courseIdSet) {
        this.courseIdSet = courseIdSet;
    }
}
