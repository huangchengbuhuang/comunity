package com.huangcheng.community.community.dto;

/**
 * @author 荒城
 * @title: Githubuser
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/417:05
 */
public class Githubuser {
    private String name;
    private Long id;
    private  String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "Githubuser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
