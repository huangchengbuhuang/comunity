package com.huangcheng.community.community.dto;

import com.huangcheng.community.community.model.User;
import lombok.Data;

/**
 * @author 荒城
 * @title: CommentSelfDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1321:28
 */
@Data
public class CommentSelfDto {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreat;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private User user;


}
