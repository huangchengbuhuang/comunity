package com.huangcheng.community.community.controller;

import com.huangcheng.community.community.dto.PagnationDto;

import com.huangcheng.community.community.service.QusestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author 荒城
 * @title: Hellocontroller
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/219:55
 */
@Controller
public class  Indexcontroller {

    @Autowired
    private QusestionService questionService;


        @GetMapping("/")
        public String index(
            Model model
            ,@RequestParam(name = "page",defaultValue = "1")Integer page
                ,@RequestParam(name = "size",defaultValue = "5")Integer size)
        {
            PagnationDto pagnationDto=questionService.list(page,size);
        model.addAttribute("pagnationDto",pagnationDto);



            return "index";
    }


}
