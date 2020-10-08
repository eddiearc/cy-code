package cn.cy.course.controller;

import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class RouterController {

    @Reference
    private CourseService courseService;

    @GetMapping("/page/course")
    public String seckillCourse(Model model) {
        // 获取本次选课的列表
        List<Course> courseList = courseService.getCourseListThisTerm();
        model.addAttribute(courseList);
        return "views/seckill_course";
    }

    @GetMapping("/page/selection")
    public String seckillSelection() {
        return "views/seckill_selection";
    }


    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
        return "views/seckill_selection";
    }

    @RequestMapping("/loginError")
    public String loginError(){
        return "views/loginError";
    }
}
