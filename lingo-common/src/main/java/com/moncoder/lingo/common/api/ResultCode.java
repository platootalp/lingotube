package com.moncoder.lingo.common.api;

/**
 * @author lenovo
 * @version 1.0
 * @description 返回结果状态码及信息
 * @date 2024/3/9 16:35
 */
public enum ResultCode implements IStatusCode{

    SUCCESS(200L,"操作成功"),
    FAILED(500L, "操作失败"),
    UNAUTHORIZED(401L, "未登录或token已经过期"),
    FORBIDDEN(403L, "无权限"),
    NOT_FOUND(404L, "资源不存在");
    /**
     * 状态码
     */
    private Long code;
    /**
     * 提示消息
     */
    private String message;

   ResultCode(Long code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Long getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
