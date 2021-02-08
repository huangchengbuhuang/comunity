package com.huangcheng.community.community.model;

import lombok.Data;

/**
 * @author 荒城
 * @title: User
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/515:29
 */
@Data
public class User {
    private Integer id;
    private  String name;
    private String account_id;

    private String token;
    private Long gmt_Creat;
    private Long gmt_Modified;
    private String avatar_url;


}
