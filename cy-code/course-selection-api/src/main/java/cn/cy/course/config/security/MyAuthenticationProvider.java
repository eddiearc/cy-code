package cn.cy.course.config.security;

import cn.cy.course.entity.UserInfo;
import cn.cy.course.service.MyUserDetailsService;
import cn.cy.course.utils.MD5Utils;
import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
    //注入我们自己定义的用户信息获取对象
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取表单输入返回的用户名
        String id = (String) authentication.getPrincipal();
        //获取密码
        String password  = (String)authentication.getCredentials();

        /**
         * 判断用户是否存在和密码是否正确
         */
        //判断用户是否存在
        UserInfo userInfo = (UserInfo) myUserDetailsService.loadUserByUsername(id);
        if(userInfo == null){
            //id不存在
            throw new BadCredentialsException("学生id不存在");
        }

        //密码加密
        if(!passwordEncoder.matches(password, userInfo.getPassword())){
            throw new BadCredentialsException("密码不正确");
        }


        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        //构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userInfo,userInfo.getPassword(),userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //true --> 表示支持这个执行
        return true;
    }
}
