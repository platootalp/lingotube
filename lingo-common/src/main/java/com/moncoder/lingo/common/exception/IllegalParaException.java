package com.moncoder.lingo.common.exception;

/**
 * @author Moncoder
 * @version 1.0
 * @description 非法参数异常
 * @date 2024/3/23 14:46
 */
public class IllegalParaException extends RuntimeException{

    private String msg;

    public IllegalParaException(){

    }

    public IllegalParaException(String msg){
        super(msg);
    }

}
