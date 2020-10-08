package cn.cy.course.controller;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.entity.LoginUser;
import cn.cy.course.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/6 10:30 下午
 */
@RestController
public class UserController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginUser loginUser) {
        AjaxResult ajaxResult = AjaxResult.success("登录成功！");
        String token = loginService.login(loginUser.getUsername(), loginUser.getPassword());
        ajaxResult.put("token", token);
        ajaxResult.put("code", 20000);
        return ajaxResult;
    }

    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.success();
    }
}
