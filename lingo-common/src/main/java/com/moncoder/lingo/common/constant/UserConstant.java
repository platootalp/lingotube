package com.moncoder.lingo.common.constant;

/**
 * @author lenovo
 * @version 1.0
 * @description 用户模块常量
 * @date 2024/3/23 13:48
 */
public interface UserConstant {

    /**
     * 注册登录验证验证码前缀
     */
    String UMS_USER_CODE = "lingo:ums:user:code:";

    /**
     * 注册登录验证码过期时间（秒）
     */
    Long UMS_USER_CODE_EXPIRE = 300L;

    /**
     * 用户名后缀
     */
    String UMS_USER_USERNAME_SUFFIX = "_linger";

    /**
     * 头像存放路径
     */
    String UMS_USER_AVATAR_PATH = "uploads/ums/avatars/";

}
