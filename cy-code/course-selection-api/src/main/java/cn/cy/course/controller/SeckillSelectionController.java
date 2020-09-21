package cn.cy.course.controller;

import cn.cy.course.entity.PageResult;
import cn.cy.course.entity.Result;
import cn.cy.course.pojo.Course;
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
    public Result add(String courseId) {
        //1. 通过Sercurity获取学号
        String studentId = "";

        //2. 课程选课包
        Pack pack = new Pack();
        pack.setStudentId(studentId);

        //3. 处理包，检查是否已经选过，检查课程是否还有剩余空位
        boolean bool = seckillSelectionService.add(pack);

        //4. 是否成功返回
        if (bool) {
            return new Result(0, "选课成功，等待20分钟后的课程确定！");
        } else {
            return new Result(1, "课程已经选完了！");
        }
    }

    /**
     * 查询选课情况
     *
     * @return
     */
    @GetMapping("/query")
    public PageResult<Selection> query(int page, int size) {
        //1. 通过Sercurity获取学号
        String studentId = "";

        //2. 获取对应的选课信息
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentId);
        PageResult<Selection> res = selectionService.findPage(map, page, size);
        //3. 根据学期排序
        List<Selection> rows = res.getRows();
        Collections.sort(rows, new Comparator<Selection>() {
            @Override
            public int compare(Selection o1, Selection o2) {
                return o2.getTerm() - o1.getTerm();
            }
        });

        return res;
    }
}
