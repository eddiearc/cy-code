package cn.cy.course.config.security;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticationProvider provider;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    //定制权限规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页所有人都可以访问
        http.authorizeRequests().antMatchers("/index.html").permitAll()
                .antMatchers("/views/seckill_course.html");


        //开启登陆功能，如果没有权限，就会自动跳转到登陆页面
        http.formLogin()
                //.usernameParameter("id")
                //.passwordParameter("password")
                //.loginPage("/login")
               // .loginProcessingUrl("/login/form")
                .successHandler(myAuthenticationSuccessHandler)
                .permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/index.html");
    }

    //过滤不需要保护的资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/data/**")
                .antMatchers("/fonts/**")
                .antMatchers("/img/**");
    }


    //定义认证规则

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }
}
