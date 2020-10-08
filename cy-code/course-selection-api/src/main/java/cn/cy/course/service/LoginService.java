package cn.cy.course.service;

import cn.cy.course.entity.LoginUser;
import cn.cy.course.entity.UserInfo;
import cn.cy.course.util.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/6 10:29 下午
 */
@Component
public class LoginService {

    @Resource
    private AuthenticationManager authenticationManager;

    public String login(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserInfo userInfo = (UserInfo) authentication.getPrincipal();

        String role = "";
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }

        return JwtTokenUtils.createToken(userInfo.getUsername(), role,true);
    }
}
