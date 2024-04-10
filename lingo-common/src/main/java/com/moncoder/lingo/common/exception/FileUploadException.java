package com.moncoder.lingo.common.exception;

/**
 * @author Moncoder
 * @version 1.0
 * @description 文件上传异常
 * @date 2024/3/26 14:26
 */
public class FileUploadException extends ApiException {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
