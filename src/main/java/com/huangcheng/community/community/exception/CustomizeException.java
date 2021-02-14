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
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode) {

        this.code=errorCode.getCode();
        this.message =errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

