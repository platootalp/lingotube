package com.moncoder.lingo.common.advice;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.exception.ApiException;
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
public class GlobalExceptionAdvice {

    @ExceptionHandler(ApiException.class)
    public Result<String> handleApiException(ApiException e){
        log.error("发生API异常->{}",e.getMessage());
        return Result.failed("内部异常");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleOtherException(Exception e){
        log.error("发生未知异常->{}",e.getMessage());
        return Result.failed("位置异常");
    }
}
