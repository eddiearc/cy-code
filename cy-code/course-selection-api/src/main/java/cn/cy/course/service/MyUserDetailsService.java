package cn.cy.course.service;

import cn.cy.course.entity.UserInfo;
import cn.cy.course.pojo.Student;
import cn.cy.course.pojo.User;
import cn.cy.course.service.StudentService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {
    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        User user = userService.findById(s);
        System.out.println(user);
        if(user != null){
            userInfo = new UserInfo(user.getId(),user.getPassword(),user.getRole(),true,true,true,true);
            System.out.println(userInfo);
        }
        System.out.println("userInfo密码:"+userInfo.getPassword());
        return userInfo;
    }
}
