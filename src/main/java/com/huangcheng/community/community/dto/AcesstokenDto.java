package com.huangcheng.community.community.dto;

import lombok.Data;

/**
 * @author 荒城
 * @title: AcesstokenDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/323:14
 */
@Data
public class AcesstokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;


}
