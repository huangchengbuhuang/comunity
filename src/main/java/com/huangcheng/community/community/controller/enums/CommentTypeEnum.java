package com.huangcheng.community.community.controller.enums;

import com.huangcheng.community.community.model.Question;

/**
 * @author 荒城
 * @title: CommentTypeEnum
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1218:51
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType()==type){
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type=type;
    }

}
