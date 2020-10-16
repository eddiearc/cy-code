package cn.cy.course.controller.stu;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @Blog https://blog.csdn.net/weixin_44129784
 * @Create 2020/10/16 12:34 下午
 * @Discription
 */
@RestController
@RequestMapping("/stu/course")
public class StuCourseController {

    @Reference
    private CourseService courseService;

    @GetMapping("/fetchListThisTerm")
    public AjaxResult fetchCourseList() {
        List<Course> courseList = courseService.getCourseListThisTerm();
        return AjaxResult.success(courseList);
    }

    /**
     * 获取课程库存
     *
     * @return
     */
    @GetMapping("/stock")
    public AjaxResult stockRealTime() {
        Map<String, Integer> courseStockRealTime = courseService.getCourseStockRealTime();
        return AjaxResult.success(courseStockRealTime);
    }
}
