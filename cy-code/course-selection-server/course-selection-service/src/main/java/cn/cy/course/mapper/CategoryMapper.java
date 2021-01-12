package cn.cy.course.mapper;

import cn.cy.course.pojo.Category;
import cn.cy.course.pojo.dto.CategoryIdCountDto;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/13 11:29 上午
 */
public interface CategoryMapper extends Mapper<Category> {


    @Select("select category_id id,count(*) count " +
            "from tb_course " +
            "where category_id is not null " +
            "group by category_id")
    /**
     * 计算不同课程分类中的课程数量
     *
     * @return
     */
    List<CategoryIdCountDto> countPeople();
}
