package cn.cy.course.controller.handler;

import cn.cy.course.entity.AjaxResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 * @author eddieVim
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public AjaxResult error(Exception e) {
        e.printStackTrace();
        System.out.println("调用了公共异常处理类");
        return AjaxResult.error(e.getMessage());
    }
}
