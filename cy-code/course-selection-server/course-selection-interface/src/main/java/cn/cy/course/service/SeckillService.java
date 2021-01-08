package cn.cy.course.service;

import cn.cy.course.pojo.Pack;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:36 下午
 */
public interface SeckillService {
    /**
     * 添加选课信息
     *
     * @param pack 选课信息
     * @return
     */
    public void add(Pack pack);

    /**
     * 移除学生的选课信息
     * 1. 数据库中移除
     * 2. 库存回滚
     * 3. redis
     *
     * @param pack 选课信息
     * @return
     */
    public void remove(Pack pack);
}
