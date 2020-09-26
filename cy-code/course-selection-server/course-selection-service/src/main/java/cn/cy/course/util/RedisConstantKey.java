package cn.cy.course.util;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/23 11:55 上午
 */
public enum RedisConstantKey {
    // 以SET方式，存储每个学生的本学期的选课情况
    SELECTION_SET,
    // 秒杀队列，用于存储每个用户的ID，与其对应的选课ID
    SECKILL_QUEUE,
    // 存储一个List对象，里面包含这次选课的所有ID
    COURSE_IDS,
    // 课程的信息，在选课时间段期间，库存信息不可靠
    COURSE_MSG_HASH,
    // 借助Redis存储每个课程的库存数量，由于Redis是单线程操作，且操作为原子性，能保证库存数量的准确性
    COURSE_STOCK_HASH,
    // 库存队列，队列存储的元素数与库存数量一致
    COURSE_STOCK_QUEUE,
    // 以HASH存储学生历史选课情况
    SELECTION_HISTORY_HASH,
    // 当前学期的标识数字
    CURR_TERM
}
