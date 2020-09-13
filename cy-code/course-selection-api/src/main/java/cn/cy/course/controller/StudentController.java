package cn.cy.course.controller;


import cn.cy.course.entity.PageResult;
import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Student;
import cn.cy.course.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author eddieVim
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Reference
    private StudentService studentService;

    @GetMapping("/findAll")
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Student> findPage(int page, int size){
        return studentService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Student> findList(@RequestBody Map<String,Object> searchMap){
        return studentService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Student> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  studentService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Student findById(String id){
        return studentService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Student student){
        studentService.add(student);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        studentService.delete(id);
        return new Result();
    }

}
