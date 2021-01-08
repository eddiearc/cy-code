package cn.cy.course.config.security;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.util.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @Author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @Blog https://blog.csdn.net/weixin_44129784
 * @Create 2020/10/14 8:56 上午
 * @Discription
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        String msg = "请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源";
        response.setContentType("text/json;charset=utf-8");
        // 返回前端错误信息
        new ObjectMapper().writeValue(response.getWriter(), AjaxResult.error(HttpStatus.UNAUTHORIZED, msg));
    }
}
