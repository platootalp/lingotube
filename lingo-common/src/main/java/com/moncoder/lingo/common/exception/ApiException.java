package com.moncoder.lingo.common.exception;


/**
 * @author Moncoder
 * @version 1.0
 * @description 通用API异常类
 * @date 2024/3/20 16:59
 */
public class ApiException extends RuntimeException{

    private String msg;

    public ApiException(){

    }

    public ApiException(String msg){
        super(msg);
    }
}
