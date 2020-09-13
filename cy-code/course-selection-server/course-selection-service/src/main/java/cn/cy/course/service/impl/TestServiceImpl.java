package cn.cy.course.service.impl;

import cn.cy.course.mapper.CourseMapper;
import cn.cy.course.pojo.Course;
import cn.cy.course.service.TestService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 11:44 上午
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String sayHello() {
        return "hello";
    }

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> getCourseList() {
        return courseMapper.selectAll();
    }
}
