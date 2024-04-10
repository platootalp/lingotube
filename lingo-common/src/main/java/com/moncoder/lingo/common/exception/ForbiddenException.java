package com.moncoder.lingo.common.exception;


import com.moncoder.lingo.common.api.IStatusCode;
import com.moncoder.lingo.common.api.ResultCode;

/**
 * @author Moncoder
 * @version 1.0
 * @description 未授权异常
 * @date 2024/4/10 16:52
 */
public class ForbiddenException extends ApiException {
    public ForbiddenException(IStatusCode statusCode) {
        super(ResultCode.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
