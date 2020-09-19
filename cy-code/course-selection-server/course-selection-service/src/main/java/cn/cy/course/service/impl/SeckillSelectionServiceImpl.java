package cn.cy.course.service.impl;

import cn.cy.course.pojo.Pack;
import cn.cy.course.service.SeckillSelectionService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/17 10:36 下午
 */
@Service(interfaceClass = SeckillSelectionService.class)
public class SeckillSelectionServiceImpl implements SeckillSelectionService {

    @Autowired
    private RedisTemplate redisTemplate;

    private String SECKILL_QUEUE = "SECKILL_QUEUE";


    @Override
    public boolean add(Pack pack) {
        //1. 检查课程库存 减少无效排队


        //2. 检查用户是否有重复的选课， Redis - set


        //3. 入队列
        Long aLong = redisTemplate.boundListOps(SECKILL_QUEUE).leftPush(pack);

        return true;
    }
}
