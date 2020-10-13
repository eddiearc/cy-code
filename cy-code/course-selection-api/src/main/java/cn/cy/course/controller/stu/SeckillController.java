package cn.cy.course.controller.stu;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.pojo.Course;
import cn.cy.course.pojo.Pack;
import cn.cy.course.service.CourseService;
import cn.cy.course.service.SeckillService;
import cn.cy.course.service.SelectionService;
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
@RequestMapping("/seckill")
public class SeckillController {

    @Reference
    private SeckillService seckillService;

    @Reference
    private SelectionService selectionService;

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

    public AjaxResult remove(String courseId, String studentId) {
        //1. 通过Sercurity获取学号
        if (studentId == null) {
            studentId  = (String) SecurityUserHelper.getCurrentPrincipal();
        }

        return AjaxResult.success("已经移除您的选课信息！");
    }

    /**
     * 查询历史选课情况
     *
     * @return
     */
    @GetMapping("/query/history")
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
    @GetMapping("/query/curr")
    public List<Course> queryCurrTerm() {
        //1. 通过Sercurity获取学号
        String studentId = (String) SecurityUserHelper.getCurrentPrincipal();

        //2. 获取对应的选课信息
        List<Course> res = selectionService.currTermSelection(studentId);


        //3. 根据学分排序
        res.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                if (o1.getCredit() == null || o2.getCredit() == null) {
                    return -1;
                }
                return o2.getCredit() - o1.getCredit();
            }
        });

        return res;
    }

    @Reference
    private CourseService courseService;

    /**
     * 获取课程库存
     *
     * @return
     */
    @GetMapping("/course/stock")
    public AjaxResult stockRealTime() {
        return courseService.getCourseStockRealTime();
    }
}
