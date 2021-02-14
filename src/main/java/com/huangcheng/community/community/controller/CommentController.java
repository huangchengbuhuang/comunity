package com.huangcheng.community.community.controller;

import com.huangcheng.community.community.Mapper.CommentMapper;
import com.huangcheng.community.community.dto.CommentDto;
import com.huangcheng.community.community.dto.ResultDto;
import com.huangcheng.community.community.exception.CustomizeErrorCode;
import com.huangcheng.community.community.model.Comment;
import com.huangcheng.community.community.model.User;
import com.huangcheng.community.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author 荒城
 * @title: CommentController
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1215:38
 */
@Controller
public class CommentController {
    @Autowired
    private  CommentService commentService;
    @ResponseBody
    @RequestMapping(value="/comment",method = RequestMethod.POST)
    public Object Post(@RequestBody CommentDto commentDto,
                       HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){
            return ResultDto.errorf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentDto == null || StringUtils.isBlank(commentDto.getContent())){

            return ResultDto.errorf(CustomizeErrorCode.CONTENT_iS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDto.getParentId());

        comment.setType(commentDto.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreat(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setContent(commentDto.getContent());
       commentService.insert(comment);
        return  ResultDto.okof();

    }

}
