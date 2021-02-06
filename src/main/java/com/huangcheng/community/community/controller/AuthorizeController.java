package com.huangcheng.community.community.controller;


import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.dto.AcesstokenDto;
import com.huangcheng.community.community.dto.Githubuser;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.provider.Githubprovider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
    private  UserMapper userMapper;

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.client.redirect.url}")
    private String client_redirect;


    @GetMapping("/callback")
    public  String callback(@RequestParam(name = "code") String code,
                                @RequestParam(name = "state") String state
                                ,HttpServletRequest request){

         AcesstokenDto acesstokenDto =  new AcesstokenDto();
         acesstokenDto.setCode(code);
         acesstokenDto.setClient_id("Iv1.2a33736e1e80c360");

         acesstokenDto.setClient_secret("6d09e49459a375754e15a70310015929271097c4");

         acesstokenDto.setRedirect_uri("http://localhost:8080/callback");
         acesstokenDto.setState(state);
        String getaccesstoken = githubprovider.getaccesstoken(acesstokenDto);

        Githubuser githubuser = githubprovider.getuser(getaccesstoken);
        if(githubuser !=null){
            //用户放入数据库
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubuser.getName());
            user.setAccount_id(String.valueOf(githubuser.getId()));
            user.setGmt_Creat(System.currentTimeMillis());
            user.setGmt_Modified(user.getGmt_Creat());
            userMapper.insert(user);



            //登陆成功，写cookie和session
            request.getSession().setAttribute("githubuser",githubuser);
            return "redirect:/";
        }else
        {
            //登陆失败，返回首页
            return "redirect:/";
        }

    }

}
