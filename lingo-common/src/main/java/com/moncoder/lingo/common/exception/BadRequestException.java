package com.moncoder.lingo.common.exception;

import com.moncoder.lingo.common.api.IStatusCode;
import com.moncoder.lingo.common.api.ResultCode;

/**
 * @author Moncoder
 * @version 1.0
 * @description 请求错误异常
 * @date 2024/4/10 16:57
 */
public class BadRequestException extends ApiException{

    public BadRequestException(IStatusCode statusCode) {
        super(ResultCode.BADREQUEST);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
