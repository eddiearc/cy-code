package cn.cy.course.controller;

import cn.cy.course.pojo.Course;
import cn.cy.course.service.TestService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 11:45 上午
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Reference
    private TestService testService;

    @RequestMapping("/sayHello")
    public String sayHello() {
        return testService.sayHello();
    }

    @RequestMapping("/getCourseList")
    public List<Course> getCourseList() {
        return testService.getCourseList();
    }
}
