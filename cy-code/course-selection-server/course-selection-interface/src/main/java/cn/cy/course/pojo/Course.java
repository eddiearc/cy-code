package cn.cy.course.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 4:00 下午
 */
@Table(name = "tb_course")
public class Course implements Serializable {

    /**
     * 课程ID
     */
    @Id
    private String id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 第几个学期，2019-2020下学期的编号为0，后面的学期递增
     */
    private Integer term;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 上课的时间
     */
    private String time;

    /**
     * 开始的周数
     */
    private Integer durationStart;

    /**
     * 结束的周数
     */
    private Integer durationEnd;

    /**
     * 上课的地点
     */
    private String place;

    /**
     * 是否是在线课程 0 是  1 否
     */
    private Integer online;

    /**
     * 教师ID
     */
    private String teacherId;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 课程数量
     */
    private Integer count;

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", term=" + term +
                ", credit=" + credit +
                ", time='" + time + '\'' +
                ", durationStart=" + durationStart +
                ", durationEnd=" + durationEnd +
                ", place='" + place + '\'' +
                ", online=" + online +
                ", teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", categoryId=" + categoryId +
                ", count=" + count +
                '}';
    }

    public Integer getDurationStart() {
        return durationStart;
    }

    public void setDurationStart(Integer durationStart) {
        this.durationStart = durationStart;
    }

    public Integer getDurationEnd() {
        return durationEnd;
    }

    public void setDurationEnd(Integer durationEnd) {
        this.durationEnd = durationEnd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

