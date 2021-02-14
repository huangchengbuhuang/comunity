package com.huangcheng.community.community.service;

import com.huangcheng.community.community.Mapper.CommentMapper;
import com.huangcheng.community.community.Mapper.QuestionExtMapper;
import com.huangcheng.community.community.Mapper.QuestionMapper;
import com.huangcheng.community.community.Mapper.UserMapper;
import com.huangcheng.community.community.controller.enums.CommentTypeEnum;
import com.huangcheng.community.community.dto.CommentSelfDto;
import com.huangcheng.community.community.exception.CustomizeErrorCode;
import com.huangcheng.community.community.exception.CustomizeException;
import com.huangcheng.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 荒城
 * @title: CommentService
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1218:56
 */
@Service
public class CommentService {

        @Autowired
            private CommentMapper commentMapper;
        @Autowired
            private QuestionMapper questionMapper;
        @Autowired
            private QuestionExtMapper questionExtMapper;
        @Autowired
        private UserMapper userMapper;
        @Transactional
    public  void insert(Comment comment) {
        if(comment.getParentId() == null){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){

            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);

        }
        if(comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbcomment = commentMapper.selectByPrimaryKey(comment.getParentId());
                if(dbcomment == null){

                    throw new CustomizeException(CustomizeErrorCode.COMMRNT_NOT_FOUND);
                }
            commentMapper.insert(comment);
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }

    }

    public List<CommentSelfDto> ListByService(Long id) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        commentExample.setOrderByClause("GMT_CREAT");
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if(comments.size()==0){
            return new ArrayList<>();
        }
        //获取去重的评论人
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        ArrayList<Long> userIds = new ArrayList();
        userIds.addAll(commentators);
        //获取评论人并转换为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment 为commentDto
        List<CommentSelfDto> commentSelfDtos = comments.stream().map(comment -> {
            CommentSelfDto commentSelfDto = new CommentSelfDto();
            BeanUtils.copyProperties(comment,commentSelfDto);
            commentSelfDto.setUser(userMap.get(comment.getCommentator()));

            return commentSelfDto;
        }).collect(Collectors.toList());
        return commentSelfDtos;

    }
}
