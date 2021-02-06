package com.huangcheng.community.community.model;

/**
 * @author 荒城
 * @title: User
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/515:29
 */
public class User {
    private Integer id;
    private  String name;
    private String account_id;

    private String token;
    private Long gmt_Creat;
    private Long gmt_Modified;

    public Integer getId() {
        return id;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Long getGmt_Creat() {
        return gmt_Creat;
    }

    public void setGmt_Creat(Long gmt_Creat) {
        this.gmt_Creat = gmt_Creat;
    }

    public Long getGmt_Modified() {
        return gmt_Modified;
    }

    public void setGmt_Modified(Long gmt_Modified) {
        this.gmt_Modified = gmt_Modified;
    }
}
