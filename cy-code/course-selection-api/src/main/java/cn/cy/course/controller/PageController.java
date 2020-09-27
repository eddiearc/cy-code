package cn.cy.course.controller;

import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/25 3:06 下午
 */
@Controller
public class PageController {

    @Reference
    private CourseService courseService;

    @GetMapping("/page/course")
    public String seckillCourse(Model model) {
        // 获取本次选课的列表
        List<Course> courseList = courseService.getCourseListThisTerm();
        model.addAttribute(courseList);
        return "/views/seckill_course";
    }

    @GetMapping("/page/selection")
    public String seckillSelection(Model model) {
        // 获取本次选课的列表
        List<Course> courseList = courseService.getCourseListThisTerm();
        model.addAttribute(courseList);
        return "/views/seckill_selection";
    }
}
