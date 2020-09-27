package cn.cy.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/views/login";
    }
}
