package cn.cy.course.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 8:57 下午
 */
@Table(name = "tb_category")
public class Category implements Serializable {

    @Id
    private String id;

    /**
     * 分类名
     */
    private String name;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
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
}
