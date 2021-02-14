package com.huangcheng.community.community.dto;

import lombok.Data;

/**
 * @author 荒城
 * @title: CommentDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1215:43
 */
@Data
public class CommentDto
{
    private  Long parentId;
    private String content;
    private Integer type;
}
