package cn.cy.course.config.security.filter;

import cn.cy.course.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/8 3:08 下午
 */
@Component
public class JwtStatusCheckoutFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // 到请求头中获取token（含Bearer字段）
        String tokenHeader = request.getHeader(tokenService.TOKEN_HEADER);

        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null ||
                !tokenHeader.startsWith(tokenService.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            System.out.println("请求头中无token!");
            return;
        }

        // 纯token字符串
        String token = tokenHeader.replace(tokenService.TOKEN_PREFIX, "");

        // 检查token是否过期，过期：直接放行
        if (tokenService.isExpiration(token)) {
            chain.doFilter(request, response);
            System.out.println("token已过期!");
            return;
        }

        // 未登录
        if (!tokenService.isLogin(token)) {
            chain.doFilter(request, response);
            System.out.println("用户登录状态已失效!");
            return;
        }

        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        chain.doFilter(request, response);
    }

    /**
     * 这里从token中获取用户信息并新建一个token认证对象
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = tokenService.getUsername(token);
        String role = tokenService.getUserRole(token);
        System.out.println("role: " + role);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null,
                    Collections.singleton(new SimpleGrantedAuthority(role))
            );
        }
        return null;
    }

}
