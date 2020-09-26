package cn.cy.course.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/21 11:30 上午
 */
@Configuration
public class AsyncThreadPoolConfig implements AsyncConfigurer {

    /**
     * 线程池中一直存活的线程数
     */
    private int CORE_POOL_SIZE = 5;

    /**
     * 最大允许的线程数
     */
    private int MAX_POOL_SIZE = 100;

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
                // 高峰期使空闲线程，存活时间长一些
                30, TimeUnit.MINUTES,
                // 队列最大容量 30
                new ArrayBlockingQueue<>(30),
                // 线程工厂
                new AsyncThreadFactory(),
                // 拒绝策略：提交任务者 自己运行
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 自定义线程工厂
     */
    private static class AsyncThreadFactory implements ThreadFactory {

        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        AsyncThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "Selection-Async-Task-Thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}

