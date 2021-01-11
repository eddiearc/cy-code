package cn.cy.course.controller.stu;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.pojo.Pack;
import cn.cy.course.service.SeckillService;
import cn.cy.course.util.SecurityUserHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:33 下午
 */
@RestController
@RequestMapping("/stu/sk")
public class SeckillController {

    @Reference
    private SeckillService seckillService;

    /**
     * 添加选课请求
     *
     * @param courseId
     * @return
     */
    @GetMapping("/add")
    public AjaxResult add(String courseId, String studentId) {
        //1. 通过Sercurity获取学号
        if (studentId == null) {
            studentId = (String) SecurityUserHelper.getCurrentPrincipal();
        }

        //2. 课程选课包
        Pack pack = new Pack();
        pack.setStudentId(studentId);
        pack.setCourseId(courseId);
        pack.setCreateTime(new Date());

        //3. 处理Pack任务
        seckillService.add(pack);

        return AjaxResult.success("您的选课信息正在排队中，请稍后到我的选课情况界面查看！");
    }

    @GetMapping("/rm")
    public AjaxResult remove(String courseId, String studentId) {
        //1. 通过Sercurity获取学号
        if (studentId == null) {
            studentId  = (String) SecurityUserHelper.getCurrentPrincipal();
        }


        return AjaxResult.success("已经移除您的选课信息！");
    }
}
