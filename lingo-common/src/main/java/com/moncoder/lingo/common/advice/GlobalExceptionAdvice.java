package com.moncoder.lingo.common.advice;

import com.moncoder.lingo.common.api.Result;
import com.moncoder.lingo.common.exception.ApiException;
import com.moncoder.lingo.common.exception.IllegalParaException;
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
        String message = e.getMessage();
        log.error("发生API异常->{}",message);
        return Result.failed(message);
    }

    @ExceptionHandler(IllegalParaException.class)
    public Result<String> handleInvalidParaException(IllegalParaException e){
        String message = e.getMessage();
        log.error("发生非法参数异常->{}",message);
        return Result.failed(message);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleOtherException(Exception e){
        String message = e.getMessage();
        log.error("发生未知异常->{}",message);
        return Result.failed("请求失败！");
    }
}
