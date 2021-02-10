package com.huangcheng.community.community.controller;

import com.huangcheng.community.community.Mapper.QuestionMapper;
import com.huangcheng.community.community.dto.QuestionDto;
import com.huangcheng.community.community.service.QusestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 荒城
 * @title: QuestionController
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1012:26
 */
@Controller
public class QuestionController {
    @Autowired
    QusestionService qusestionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model){
        QuestionDto questionDto =qusestionService.getById(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
