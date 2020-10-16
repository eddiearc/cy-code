package cn.cy.course.controller.stu;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.pojo.Course;
import cn.cy.course.service.SelectionService;
import cn.cy.course.util.SecurityUserHelper;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @Blog https://blog.csdn.net/weixin_44129784
 * @Create 2020/10/16 12:45 下午
 * @Discription
 */
@RestController
@RequestMapping("/stu/selection")
public class StuSelectionController {

    @Reference
    private SelectionService selectionService;

    /**
     * 查询历史选课情况
     *
     * @return
     */
    @GetMapping("/history")
    public List<Course> queryHistory() {
        //1. 通过Sercurity获取学号
        String studentId = (String) SecurityUserHelper.getCurrentPrincipal();

        //2. 获取对应的选课信息
        List<Course> res = selectionService.historySelection(studentId);
        //3. 根据学期排序；同学期，根据学分排序
        Collections.sort(res, new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.getTerm() == null ||
                        o2.getTerm() == null ||
                        o1.getTerm().equals(o2.getTerm())
                ) {
                    return o2.getCredit() - o1.getCredit();
                }
                return o2.getTerm() - o1.getTerm();
            }
        });

        return res;
    }

    /**
     * 查询本学期选课情况
     *
     * @return
     */
    @GetMapping("/current")
    public AjaxResult queryCurrentTerm() {
        //1. 通过Sercurity获取学号
        String studentId = (String) SecurityUserHelper.getCurrentPrincipal();

        //2. 获取对应的选课信息
        Map<String, Course> stringCourseMap = selectionService.currentTermSelection(studentId);

        return AjaxResult.success(stringCourseMap);
    }
}
