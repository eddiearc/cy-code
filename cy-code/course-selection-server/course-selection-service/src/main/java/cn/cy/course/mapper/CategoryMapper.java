package cn.cy.course.mapper;

import cn.cy.course.pojo.Category;
import cn.cy.course.vo.CategoryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/13 11:29 上午
 */
public interface CategoryMapper extends Mapper<Category> {

    @Select("select category_id as id,count(*) as count\n" +
            "from tb_course\n" +
            "group by category_id")
    public List<CategoryVo> countProple();
}
