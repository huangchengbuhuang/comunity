package com.huangcheng.community.community.controller;

import com.huangcheng.community.community.dto.AcesstokenDto;
import com.huangcheng.community.community.dto.Githubuser;
import com.huangcheng.community.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

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

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.client.redirect.url}")
    private String client_redirect;

    @GetMapping("/callback")
    public <re> String callback(@RequestParam(name = "code") String code,
                                @RequestParam(name = "state") String state
                                ,HttpServletRequest request){

         AcesstokenDto acesstokenDto =  new AcesstokenDto();
         acesstokenDto.setCode(code);
         acesstokenDto.setClient_id("client_id");
         acesstokenDto.setClient_secret("client.secret");

         acesstokenDto.setRedirect_uri("github.client.redirect.url");
         acesstokenDto.setState(state);
        String getaccesstoken = githubprovider.getaccesstoken(acesstokenDto);
        Githubuser user = githubprovider.getuser(getaccesstoken);
        if(user !=null){
            //登陆成功，写cookie和session
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }else
        {
            //登陆失败，返回首页
            return "redirect:/";
        }

    }

}
