package cn.cy.course.controller.manager;


import cn.cy.course.entity.PageResult;
import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.SelectionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author eddieVim
 */
@RestController
@RequestMapping("/selection")
public class SelectionController {

    @Reference
    private SelectionService selectionService;

    @GetMapping("/findAll")
    public List<Selection> findAll(){
        return selectionService.findAll();
    }

    @GetMapping("/findPage")
    public PageResult<Selection> findPage(int page, int size){
        return selectionService.findPage(page, size);
    }

    @PostMapping("/findList")
    public List<Selection> findList(@RequestBody Map<String,Object> searchMap){
        return selectionService.findList(searchMap);
    }

    @PostMapping("/findPage")
    public PageResult<Selection> findPage(@RequestBody Map<String,Object> searchMap,int page, int size){
        return  selectionService.findPage(searchMap,page,size);
    }

    @GetMapping("/findByCourseId")
    public List<Selection> findByCourseId(String courseId) {
        return selectionService.findByStudentId(courseId);
    }

    @GetMapping("/findByStudentId")
    public List<Selection> findByStudentId(String studentId) {
        return selectionService.findByStudentId(studentId);
    }

    @PostMapping("/add")
    public Result add(@RequestBody Selection selection){
        selectionService.add(selection);
        return new Result();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Selection selection){
        selectionService.update(selection);
        return new Result();
    }

    @GetMapping("/delete")
    public Result delete(String studentId, String courseId){
        selectionService.delete(studentId, courseId);
        return new Result();
    }
}
