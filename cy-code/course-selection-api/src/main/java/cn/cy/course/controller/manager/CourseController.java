package cn.cy.course.controller.manager;


import cn.cy.course.entity.PageResult;
import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Course;
import cn.cy.course.service.CourseService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Reference
    private CourseService courseService;

    @GetMapping("/findAll")
    public List<Course> findAll(){
        return courseService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Course> findPage(int page, int size){
        return courseService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Course> findList(@RequestBody Map<String,Object> searchMap){
        return courseService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Course> findPage(@RequestBody(required = false) Map<String,Object> searchMap,int page, int size){
        System.out.println(page + "  " + size);
        return  courseService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Course findById(String id){
        return courseService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Course course){
        courseService.add(course);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Course course){
        courseService.update(course);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        courseService.delete(id);
        return new Result();
    }

    //根据教师id查询教师授课信息
    @GetMapping("/getDetailInfo")
    public List<Course> getDetailInfo(String teacherId){
        List<Course> courseList = courseService.getInfoByTeacherId(teacherId);
        return courseList;
    }
}
