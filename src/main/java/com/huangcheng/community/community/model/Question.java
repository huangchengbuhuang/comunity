package com.huangcheng.community.community.model;

import lombok.Data;

/**
 * @author 荒城
 * @title: Question
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/716:23
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long   gmt_create;
    private Long   gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;

}
