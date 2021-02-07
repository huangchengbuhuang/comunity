package com.huangcheng.community.community.controller;
import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 荒城
 * @title: Hellocontroller
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/219:55
 */
@Controller
public class Indexcontroller {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token"))
            {
                String token = cookie.getValue();
                User user = userMapper.findToken(token);
                if(user!=null)
                {
                    request.getSession().setAttribute("user",user);
                }
                break;

            }
        }

        return "index";
    }


}
