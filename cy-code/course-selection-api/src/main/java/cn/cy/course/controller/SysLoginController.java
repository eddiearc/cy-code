package cn.cy.course.controller;

import cn.cy.course.entity.AjaxResult;
import cn.cy.course.entity.LoginUser;
import cn.cy.course.service.SysLoginService;
import cn.cy.course.util.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * @author eddieVim
 * @微信公众号 艾迪威姆 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/6 10:30 下午
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("login")
    public AjaxResult login(@RequestBody LoginUser loginUser) {
        AjaxResult ajaxResult = AjaxResult.success("登录成功！");
        String token = sysLoginService.login(loginUser.getUsername(), loginUser.getPassword());
        ajaxResult.put("token", token);
        return ajaxResult;
    }

    @GetMapping("getInfo")
    public AjaxResult getInfo(HttpServletRequest request) {
        // 获取
        String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);

        // 如果请求头中没有Authorization信息则返回对应信息
        if (tokenHeader == null ||
                !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            System.out.println("请求头中无token或已过期！");
            return AjaxResult.error("认证失效！");
        }

        // 检查是否过期
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        if (JwtTokenUtils.isExpiration(token)) {
            return AjaxResult.error("认证过期！");
        }

        // 获取对应的信息
        String username = JwtTokenUtils.getUsername(token);
        String role = JwtTokenUtils.getUserRole(token);

        ArrayList<String> roles = new ArrayList<>(1);
        roles.add(role);

        AjaxResult res = AjaxResult.success();

        res.put("name", username);
        res.put("roles", roles);

        return res;
    }
}
