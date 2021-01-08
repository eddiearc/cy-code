package cn.cy.course.service;

import cn.cy.course.pojo.Course;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 11:43 上午
 */
public interface TestService {

    String sayHello();

    List<Course> getCourseList();
}
