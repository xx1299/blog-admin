package com.xx.bootblog.domain.dto;

import com.xx.bootblog.common.exception.CustomException;
import lombok.Data;

@Data
public class AjxsResponse<T> {

    private String message;

    private Integer code;

    private T data;

    public static AjxsResponse success(){
        AjxsResponse ajxsResponse = new AjxsResponse();
        ajxsResponse.setCode(200);
        ajxsResponse.setMessage("请求成功");
        return ajxsResponse;
    }

    public static <T>AjxsResponse success(T data){
        AjxsResponse ajxsResponse = new AjxsResponse();
        ajxsResponse.setCode(200);
        ajxsResponse.setMessage("请求成功");
        ajxsResponse.setData(data);
        return ajxsResponse;
    }

    public static <T>AjxsResponse success(String message, T data){
        AjxsResponse ajxsResponse = new AjxsResponse();
        ajxsResponse.setCode(200);
        ajxsResponse.setMessage(message);
        ajxsResponse.setData(data);
        return ajxsResponse;
    }

    public static AjxsResponse error(CustomException exception){
        AjxsResponse ajxsResponse = new AjxsResponse();
        ajxsResponse.setCode(exception.getCode());
        ajxsResponse.setMessage(exception.getMessage());
        return ajxsResponse;
    }

    public static AjxsResponse error(CustomException exception,String message){
        AjxsResponse ajxsResponse = new AjxsResponse();
        ajxsResponse.setCode(exception.getCode());
        ajxsResponse.setMessage(message);
        return ajxsResponse;
    }

}
