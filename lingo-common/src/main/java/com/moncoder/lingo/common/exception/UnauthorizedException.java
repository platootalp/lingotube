package com.moncoder.lingo.common.exception;

import com.moncoder.lingo.common.api.ResultCode;

/**
 * @author Moncoder
 * @version 1.0
 * @description 未认证异常
 * @date 2024/4/9 14:57
 */
public class UnauthorizedException extends ApiException{

    public UnauthorizedException() {
        super(ResultCode.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message,cause);
    }
}
