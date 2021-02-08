package com.huangcheng.community.community.controller;
import com.huangcheng.community.community.Mapper.QuestionMapper;
import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.dto.QuestionDto;
import com.huangcheng.community.community.model.Question;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.service.QusestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @Autowired
    private QusestionService questionService;


        @GetMapping("/")
        public String index(HttpServletRequest request
            ,Model model)
        {

            Cookie[] cookies = request.getCookies();
            if(cookies!=null&&cookies.length!=0)//判断cookies是否为空
            {
                for (Cookie cookie : cookies) {//取出cookie并进行比较
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
            }

            List<QuestionDto> questoinlist=questionService.list();
        model.addAttribute("questions",questoinlist);
        System.out.print(questoinlist.toString());


        return "index";
    }


}
