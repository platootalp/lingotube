package com.moncoder.lingo.common.util;

import io.swagger.models.auth.In;

/**
 * @author Moncoder
 * @version 1.0
 * @description 当前线程处理的用户信息
 * @date 2024/4/17 16:40
 */
public class UserContext {

    private static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 保存当前登录用户信息到ThreadLocal
     *
     * @param userId 用户id
     */
    public static void setUser(Integer userId) {
        THREAD_LOCAL.set(userId);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return 用户id
     */
    public static Integer getUser() {
        return THREAD_LOCAL.get();
    }

    /**
     * 移除当前登录用户信息
     */
    public static void removeUser() {
        THREAD_LOCAL.remove();
    }
}
