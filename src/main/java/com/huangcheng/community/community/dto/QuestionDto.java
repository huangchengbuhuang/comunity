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
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long   gmtCreate;
    private Long   gmtModified;
    private Long creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
