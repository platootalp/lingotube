package com.moncoder.lingo.common.aspect;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.exception.*;
import com.moncoder.lingo.common.exception.IllegalArgumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Moncoder
 * @version 1.0
 * @description 全局异常处理器
 * @date 2024/3/21 10:49
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAspect {

    @ExceptionHandler(ApiException.class)
    public Result<String> handleApiException(ApiException e) {
        String message = e.getMessage();
        log.error("API异常->{}", message);
        e.printStackTrace();
        return Result.failed(message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();
        log.error("非法参数异常->{}", message);
        e.printStackTrace();
        return Result.failed(message);
    }

    @ExceptionHandler(FileUploadException.class)
    public Result<String> handleFileUploadException(Exception e) {
        String message = e.getMessage();
        log.error("文件上传异常->{}", message);
        e.printStackTrace();
        return Result.failed(message);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Result<String> handleUnauthorizedException(Exception e) {
        String message = e.getMessage();
        log.error("未认证->{}", message);
        e.printStackTrace();
        return Result.unauthorized(message);
    }

    @ExceptionHandler(ForbiddenException.class)
    public Result<String> handleForbiddenException(Exception e) {
        String message = e.getMessage();
        log.error("未授权->{}", message);
        e.printStackTrace();
        return Result.forbidden(message);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleOtherException(Exception e) {
        String message = e.getMessage();
        log.error("其他异常->{}", message);
        e.printStackTrace();
        return Result.failed("请求失败！");
    }
}
