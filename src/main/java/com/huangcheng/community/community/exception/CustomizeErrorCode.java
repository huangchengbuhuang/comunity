package com.huangcheng.community.community.exception;

/**
 * @author 荒城
 * @title: CustomizeErrorCode
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1210:33
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不在了，要不换个试试？");
    @Override
    public String getMessage() {
        return message;
    }
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }


}
