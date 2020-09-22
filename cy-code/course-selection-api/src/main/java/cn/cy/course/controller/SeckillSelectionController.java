package cn.cy.course.controller;

import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Pack;
import cn.cy.course.pojo.Selection;
import cn.cy.course.service.SeckillSelectionService;
import cn.cy.course.service.SelectionService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:33 下午
 */
@RestController
@RequestMapping("/seckill/selection")
public class SeckillSelectionController {

    @Reference
    private SeckillSelectionService seckillSelectionService;

    @Reference
    private SelectionService selectionService;

    /**
     * 添加选课请求
     *
     * @param courseId
     * @return
     */
    @GetMapping("/add")
    public Result add(String courseId, String stuId) {
        String studentId = stuId;
        //1. 通过Sercurity获取学号
        // String studentId = "201841054085";

        //2. 课程选课包
        Pack pack = new Pack();
        pack.setStudentId(studentId);
        pack.setCourseId(courseId);

        //3. 处理Pack任务
        seckillSelectionService.add(pack);

        return new Result(0, "选课排队中！");
    }

    /**
     * 查询选课情况
     *
     * @return
     */
    @GetMapping("/query")
    public List<Selection> query() {
        //1. 通过Sercurity获取学号
        String studentId = "201841054085";

        //2. 获取对应的选课信息
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        List<Selection> res = selectionService.findList(map);
        //3. 根据学期排序
        Collections.sort(res, new Comparator<Selection>() {
            @Override
            public int compare(Selection o1, Selection o2) {
                return o2.getTerm() - o1.getTerm();
            }
        });

        return res;
    }
}
