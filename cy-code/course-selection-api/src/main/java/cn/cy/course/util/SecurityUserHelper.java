package cn.cy.course.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/10 9:47 下午
 */
public class SecurityUserHelper {

    /**
     * 获取当前用户
     *
     * @return
     */
    public static Authentication getCurrentUserAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户用户名
     *
     * @return
     */
    public static Object getCurrentPrincipal() {
        return getCurrentUserAuthentication().getPrincipal();
    }
}