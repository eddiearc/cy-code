package cn.cy.course.controller.teach;

import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Selection;
import cn.cy.course.pojo.Student;
import cn.cy.course.service.CourseService;
import cn.cy.course.service.SelectionService;
import cn.cy.course.service.StudentService;
import cn.cy.course.service.TeacherService;
import cn.cy.course.util.SecurityUserHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @Reference
    private SelectionService selectionService;

    @Reference
    private StudentService studentService;

    @GetMapping("/course")
    public List<Course> teachCourse() {
        String teacherId = (String) SecurityUserHelper.getCurrentPrincipal();
        return courseService.teachCourse(teacherId);
    }

    /**
     * 根据课程id查询选课学生信息
     * @param id
     * @return
     */
    @GetMapping("/getStudentList")
    public List<Student> getStudentList(String id){
        List<Student> studentList = new ArrayList<>();
        List<Selection> selectionList = selectionService.findByCourseId(id);
        for(Selection selection : selectionList){
            String studentId = selection.getStudentId();
            Student student = studentService.findById(studentId);
            if(student != null){
                studentList.add(student);
            }
        }
        return studentList;
    }
}
