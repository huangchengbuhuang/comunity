package com.huangcheng.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 荒城
 * @title: AuthorizeController
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/323:00
 */
@Controller
public class AuthorizeController {
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
    return  "index";
    }

}
