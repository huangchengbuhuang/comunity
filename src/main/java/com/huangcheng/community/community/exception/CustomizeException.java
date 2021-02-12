package com.huangcheng.community.community.exception;

/**
 * @author 荒城
 * @title: CustomizeException
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/129:56
 */
public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message =errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

