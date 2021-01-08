package cn.cy.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/9/29 4:41 下午
 */
@Service
public class ThymeleafService {
    // 自己手动创建一个存在的文件路径
    @Value("dest-path")
    public static String DEST_PATH;

    @Autowired
    private TemplateEngine templateEngine;

    public void createHtml(Long id) {
        // 上下文
        Context context = new Context();
        // context.setVariables();
        // 输出流
        File dest = new File(DEST_PATH, id + ".html");
        if (dest.exists()) {
            dest.delete();
        }
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            // 生成html，第一个参数是thymeleaf页面下的原型名称
            templateEngine.process("id", context, writer);
        } catch (Exception e) {
            // log.error("[静态页服务]：生成静态页异常", e);
        }
    }

    public void deleteHtml(Long id) {

    }

}
