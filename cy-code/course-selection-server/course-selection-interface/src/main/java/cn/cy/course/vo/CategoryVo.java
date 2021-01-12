package cn.cy.course.vo;

import java.io.Serializable;

/**
 * @author AnxuVim
 * @version 1.0
 * @date 2021/1/11 17:01
 */
public class CategoryVo implements Serializable {
    //课程分类id
    private Integer id;

    //课程分类名称
    private String name;

    //课程总数
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
