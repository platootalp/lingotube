package com.moncoder.lingo.common.api;

/**
 * @author lenovo
 * @version 1.0
 * @description 状态码接口，定义状态码行为
 * @date 2024/3/9 16:42
 */
public interface IStatusCode {

    Long getCode();

    String getMessage();
}
