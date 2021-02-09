package com.huangcheng.community.community.service;

import com.huangcheng.community.community.Mapper.QuestionMapper;
import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.dto.PagnationDto;
import com.huangcheng.community.community.dto.QuestionDto;
import com.huangcheng.community.community.model.Question;
import com.huangcheng.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 荒城
 * @title: QusestionService
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/812:34
 */
@Service
public class QusestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PagnationDto list(Integer page, Integer size) {


        PagnationDto pagnationDto = new PagnationDto();
        Integer totalcount= questionMapper.count();
        pagnationDto.setTotalPage(1);//这里仅表示初始化，否则出现空指针异常，totalpage会更新

        if(totalcount%size==0)
        {
            pagnationDto.setTotalPage(totalcount/size);
        }else{
            pagnationDto.setTotalPage(totalcount/size+1);
        }
        if(page<1){//判断是否有页面越界
            page=1;
        }
        if(page > pagnationDto.getTotalPage()){
            page=pagnationDto.getTotalPage();
        }
        pagnationDto.setPagination(totalcount,page,size);


        //size*(page-1)
        int offset = size * (page - 1);//偏移量基址
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();

        for (Question question : questions) {
           User user =userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        pagnationDto.setQuestions(questionDtoList);

        return pagnationDto;
    }
}
