package com.moncoder.lingo.common.exception;

/**
 * @author Moncoder
 * @version 1.0
 * @description 文件上传异常
 * @date 2024/3/26 14:26
 */
public class FileUploadException extends RuntimeException{

    private String msg;

    public FileUploadException(){

    }
    public FileUploadException(String msg){
        super(msg);
    }
}
