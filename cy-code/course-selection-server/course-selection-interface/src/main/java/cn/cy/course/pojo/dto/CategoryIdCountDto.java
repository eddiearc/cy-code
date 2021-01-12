package cn.cy.course.pojo.dto;

/**
 * @Author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @Blog https://blog.csdn.net/weixin_44129784
 * @Create 2021/1/12 11:32
 * @Discription
 */
public class CategoryIdCountDto {
    private Integer id;
    private Integer count;

    @Override
    public String toString() {
        return "CategoryIdCountDto{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
