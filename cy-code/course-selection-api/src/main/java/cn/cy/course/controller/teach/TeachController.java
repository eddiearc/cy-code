package cn.cy.course.controller.teach;

import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import cn.cy.course.util.SecurityUserHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @Blog https://blog.csdn.net/weixin_44129784
 * @Create 2020/10/11 4:25 下午
 * @Discription
 */
@RestController
@RequestMapping("/teach")
public class TeachController {

    @Reference
    private CourseService courseService;

    @GetMapping("/course")
    public List<Course> teachCourse() {
        String teacherId = (String) SecurityUserHelper.getCurrentPrincipal();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("teacherId", teacherId);
        return courseService.findList(searchMap);
    }
}
