package com.huangcheng.community.community.dto;

import com.huangcheng.community.community.model.User;
import lombok.Data;

/**
 * @author 荒城
 * @title: QuestionDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/812:32
 */
@Data
public class QuestionDto {
    private int id;
    private String title;
    private String description;
    private String tag;
    private Long   gmt_create;
    private Long   gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private User user;
}
