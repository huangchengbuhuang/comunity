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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 荒城
 * @title: Pulishcontroller
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/714:18
 */
@Controller
public class Pulishcontroller {

    @Autowired
    private QusestionService qusestionService;
@GetMapping("/publish/{id}")
public String edit(@PathVariable(name="id") Integer id,
                   Model model){
    QuestionDto question = qusestionService.getById(id);
    model.addAttribute("title",question.getTitle());
    model.addAttribute("descripton",question.getDescription());
    model.addAttribute("tag",question.getTag());
    model.addAttribute("id",question.getId());
    return  "publish";
}

    @GetMapping("/publish")
    public String publish()
    {
        return  "publish";
    }
    @PostMapping("/publish")
    public String doPublish (//发布页面
            @RequestParam(value = "title" ,required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false)Integer id,
            HttpServletRequest request,
          Model model) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title==null || title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description==null || description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag==null || tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }


        //获取cookie来查询用户信息
        User user=(User)request.getSession().getAttribute("user");
        if(user==null)//如果没有用户信息的话，那么提示用户未登录
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }


        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        //在此处判断更新问题还是创建问题因为使用的都是publish的页面进行的
        qusestionService.createOrupdate(question);

        return "redirect:/";
    }
}
