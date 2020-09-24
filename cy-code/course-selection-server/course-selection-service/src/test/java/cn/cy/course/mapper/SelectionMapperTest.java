package cn.cy.course.mapper;

import cn.cy.course.pojo.Selection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/24 12:30 下午
 */
@SpringBootTest
@AutoConfigureTestDatabase
class SelectionMapperTest {

    @Autowired(required = false)
    private SelectionMapper selectionMapper;

    @Test
    public void fun() {
        Selection selection = new Selection();
        selection.setStudentId("1");
        selection.setCourseId("123");
        selection.setTerm(0);
        selection.setCreateTime(new Date());
        selectionMapper.insert(selection);
    }

}