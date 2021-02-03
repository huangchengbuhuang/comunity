package com.huangcheng.community.community.controller;
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
public class Indexcontroller {

    @GetMapping("/")
    public String index()
    {

        return "index";
    }


}
