package cn.cy.course.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/20 2:15 下午
 */
@SpringBootTest
class CreateSelectionExcutorTest {

    @Autowired
    private CreateSelectionExcutor createSelectionExcutor;

    @Test
    void createSelection() {
        createSelectionExcutor.createSelection();
    }
}