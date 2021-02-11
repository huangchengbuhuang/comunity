package com.huangcheng.community.community.controller;


import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.dto.AcesstokenDto;
import com.huangcheng.community.community.dto.Githubuser;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.provider.Githubprovider;
import com.huangcheng.community.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author 荒城
 * @title: AuthorizeController
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/323:00
 */
@Controller
public class AuthorizeController {
    @Autowired
    private Githubprovider githubprovider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.client.redirect.url}")
    private String client_redirect;


    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                            @RequestParam(name = "state") String state
                                , HttpServletRequest request
                                , HttpServletResponse response){

         AcesstokenDto acesstokenDto =  new AcesstokenDto();
         acesstokenDto.setCode(code);
         acesstokenDto.setClient_id(client_id);
         acesstokenDto.setClient_secret(client_secret);
         acesstokenDto.setRedirect_uri(client_redirect);
         acesstokenDto.setState(state);
        String getaccesstoken = githubprovider.getaccesstoken(acesstokenDto);

        Githubuser githubuser = githubprovider.getuser(getaccesstoken);
        if(githubuser !=null&&githubuser.getId()!=null){
            //用户放入数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setAvatarUrl(githubuser.getAvatarUrl());
            userService.createOrupdate(user);

            response.addCookie(new Cookie("token",token));



            //登陆成功，写cookie和session
//            request.getSession().setAttribute("githubuser",githubuser);
            return "redirect:/";
        }else
        {
            //登陆失败，返回首页
            return "redirect:/";
        }



    }

    //退出登录
    @GetMapping("/logout")
   public String logout(HttpServletRequest request,
                        HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

       return "redirect:/";
   }

}
