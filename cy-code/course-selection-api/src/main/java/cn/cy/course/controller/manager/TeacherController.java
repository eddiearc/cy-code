package cn.cy.course.controller.manager;


import cn.cy.course.entity.PageResult;
import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Teacher;
import cn.cy.course.service.TeacherService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author eddieVim
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Reference
    private TeacherService teacherService;

    @GetMapping("/findAll")
    public List<Teacher> findAll(){
        return teacherService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Teacher> findPage(int page, int size){
        return teacherService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Teacher> findList(@RequestBody Map<String,Object> searchMap){
        return teacherService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Teacher> findPage(@RequestBody(required = false) Map<String,Object> searchMap,int page, int size){
        return  teacherService.findPage(searchMap,page,size);
    }

    @GetMapping("/findById")
    public Teacher findById(String id){
        return teacherService.findById(id);
    }


    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){
        teacherService.add(teacher);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher){
        teacherService.update(teacher);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String id){
        teacherService.delete(id);
        return new Result();
    }

}
