package com.huangcheng.community.community.controller;

import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.dto.PagnationDto;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.service.QusestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 荒城
 * @title: Profilecontroller
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/920:04
 */
@Controller
public class Profilecontroller {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QusestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request
            ,@RequestParam(name = "page",defaultValue = "1")Integer page
            ,@RequestParam(name = "size",defaultValue = "5")Integer size){
        User user=null;
        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length!=0)//判断cookies是否为空
        {
            for (Cookie cookie : cookies) {//取出cookie并进行比较
                if(cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                      user = userMapper.findToken(token);
                    if(user!=null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if( user == null){
            return  "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PagnationDto pagnationDto =questionService.list(user.getId(),page,size);
        model.addAttribute("pagnationDto",pagnationDto);

        return "profile";
    }
}
