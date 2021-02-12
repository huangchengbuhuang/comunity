package com.huangcheng.community.community.advice;

import com.huangcheng.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 荒城
 * @title: CustomizeError
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/129:41
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model){
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());
        }else {
            model.addAttribute("message","服务器冒烟了稍后再试试！！！");

        }
        return new ModelAndView("error");
    }

}




