package com.huangcheng.community.community.dto;

import com.huangcheng.community.community.exception.CustomizeErrorCode;
import com.huangcheng.community.community.exception.CustomizeException;
import lombok.Data;

/**
 * @author 荒城
 * @title: ResultDto
 * @projectName haungcommunity
 * @description: TODO
 * @date 2021/2/1217:18
 */
@Data
public class ResultDto {
    private Integer code;
    private String message;
    public static ResultDto errorf(Integer code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }

   public static ResultDto errorf(CustomizeErrorCode errorCode) {
        return errorf(errorCode.getCode(),errorCode.getMessage());
    }
    public static ResultDto errorf(CustomizeException e) {
        return errorf(e.getCode(),e.getMessage());
    }
    public static ResultDto okof() {
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }



}
