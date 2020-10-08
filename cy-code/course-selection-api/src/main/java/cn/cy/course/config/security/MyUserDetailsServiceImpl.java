package cn.cy.course.config.security;

import cn.cy.course.entity.UserInfo;
import cn.cy.course.pojo.Student;
import cn.cy.course.pojo.User;
import cn.cy.course.service.StudentService;
import cn.cy.course.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("myUserDetailsServiceImpl")
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findById(s);
        if (user == null) {
            return null;
        }
        return new UserInfo(user);
    }
}
