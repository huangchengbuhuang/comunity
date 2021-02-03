package com.huangcheng.community.community.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 荒城
 * @title: Hellocontroller
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/219:55
 */
@Controller
public class Hellocontroller {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name, Model model)
    {
       model.addAttribute("name",name);
          System.out.printf(name);
        return "gretting";
    }


}
