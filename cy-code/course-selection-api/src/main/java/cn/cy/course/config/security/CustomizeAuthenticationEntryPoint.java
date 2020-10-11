package cn.cy.course.config.security;

import cn.cy.course.entity.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/6 4:15 下午
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResult ajaxResult = AjaxResult.error("匿名用户无权限访问该资源");
        httpServletResponse.setContentType("text/json;charset=utf-8");
        new ObjectMapper().writeValue(httpServletResponse.getWriter(), ajaxResult);
    }
}
