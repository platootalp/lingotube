package com.moncoder.lingo.common.exception;

import com.moncoder.lingo.common.api.IStatusCode;

/**
 * @author Moncoder
 * @version 1.0
 * @description 通用API异常类
 * @date 2024/3/20 16:59
 */
public class ApiException extends RuntimeException {

    private IStatusCode statusCode;

    public ApiException(IStatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public IStatusCode getErrorCode() {
        return statusCode;
    }

}
