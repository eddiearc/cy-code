package cn.cy.course.config.security.handler;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.entity.LoginUser;
import cn.cy.course.service.TokenService;
import cn.cy.course.util.HttpStatus;
import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/8 3:41 下午
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
            // get token
            String tokenHeader = request.getHeader(tokenService.TOKEN_HEADER);
            String token = tokenHeader.startsWith(tokenService.TOKEN_PREFIX) ?
                    tokenHeader.replace(tokenService.TOKEN_PREFIX, "") : null;

            if (token == null) {
                throw new RuntimeException("认证消息已经失效！");
            }

            tokenService.delToken(token);
        }
}
