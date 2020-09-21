package cn.cy.course;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/12 11:41 上午
 */
@SpringBootApplication
@EnableAsync
@MapperScan("cn.cy.course.mapper")
public class CourseServiceProvider {
    public static void main(String[] args) {
        new SpringApplicationBuilder(CourseServiceProvider.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}
