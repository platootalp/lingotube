package com.moncoder.lingo.common.constant;

/**
 * @author lenovo
 * @version 1.0
 * @description 用户模块常量
 * @date 2024/3/23 13:48
 */
public interface UserConstant {

    /**
     * 注册登录验证码前缀
     */
    String UMS_USER_CODE = "lingo:ums:user:code:";

    /**
     * 注册登录验证码过期时间（秒）
     */
    Long UMS_USER_CODE_EXPIRE = 300L;

    /**
     * 验证码邮件标题
     */
    String UMS_USER_CODE_MAIL_SUBJECT = "来自LingoTube的邮件";

    /**
     * 验证码邮件内容
     */
    String UMS_USER_CODE_MAIL_CONTENT = "您正在进行验证，您的验证码是：";

    /**
     * 用户名后缀
     */
    String UMS_USER_USERNAME_SUFFIX = "_linger";

    /**
     * 头像存放路径
     */
    String UMS_USER_AVATAR_PATH = "uploads/ums/avatars/";

}
