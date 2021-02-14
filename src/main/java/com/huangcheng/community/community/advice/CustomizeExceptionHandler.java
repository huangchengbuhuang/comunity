package com.huangcheng.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.huangcheng.community.community.dto.ResultDto;
import com.huangcheng.community.community.exception.CustomizeErrorCode;
import com.huangcheng.community.community.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request,
                  HttpServletResponse response){
        String contentType = request.getContentType();
        if("application/json".equals(contentType)){
            //返回json
            ResultDto resultDto;
            if (e instanceof CustomizeException){
                resultDto= ResultDto.errorf((CustomizeException)e);
            }
            else {
                resultDto= ResultDto.errorf(CustomizeErrorCode.SYS_ERROR);
            }
            //将modelandview转换成json
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer=response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            }catch (IOException ioe){

            }
            return  null;

        }else {
            //错误页面跳转
            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else {
                model.addAttribute("message","服务器冒烟了稍后再试试！！！");
            }
            return new ModelAndView("error");
        }

    }

}




