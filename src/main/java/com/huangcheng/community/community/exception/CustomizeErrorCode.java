package com.huangcheng.community.community.exception;

/**
 * @author 荒城
 * @title: CustomizeErrorCode
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1210:33
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不在了，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后在进行操作"),
    SYS_ERROR(2004,"服务器冒烟了稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在"),
    COMMRNT_NOT_FOUND(2006,"回复的评论不存在，要不换个试试？"),
    CONTENT_iS_EMPTY(2007,"评论不能为空")
    ;


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code,String message) {

        this.message = message;
        this.code= code;
    }



}
