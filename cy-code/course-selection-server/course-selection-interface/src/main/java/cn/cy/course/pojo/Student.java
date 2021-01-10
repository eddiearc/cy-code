package cn.cy.course.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 4:00 下午
 */
@Table(name = "tb_student")
public class Student implements Serializable {

    /**
     * 学生学号
     */
    @Id
    private String id;

    /**
     * 学生姓名
     */
    private String name;


    /**
     * 性别：0 女， 1 男
     */
    private Integer sex;

    /**
     * 居民身份证
     */
    private String idNumber;

    /**
     * 学院、系
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    @Column(name = "class")
    private String clazz;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", idNumber='" + idNumber + '\'' +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
}
