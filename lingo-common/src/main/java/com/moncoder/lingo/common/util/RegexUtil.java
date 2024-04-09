package com.moncoder.lingo.common.util;

import cn.hutool.core.util.StrUtil;

/**
 * @author Moncoder
 * @version 1.0
 * @description 对字符串进行正则表达式验证
 * @date 2023/11/30 19:08
 */
public class RegexUtil {

    /**
     * 手机号正则
     */
    public static final String PHONE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 邮箱正则
     */
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /**
     * 密码正则。4~32位的字母、数字、下划线
     */
    public static final String PASSWORD_REGEX = "^\\w{4,32}$";
    /**
     * 验证码正则, 6位数字
     */
    public static final String VERIFY_CODE_REGEX = "^[\\d]{6}$";

    /**
     * 是否是有效的手机号格式
     * @param phone 要校验的手机号
     * @return true:有效，false：无效
     */
    public static boolean isPhone(String phone){
        return match(phone, PHONE_REGEX);
    }

    /**
     * 是否是有效的邮箱格式
     * @param email 要校验的邮箱
     * @return true:有效，false：无效
     */
    public static boolean isEmail(String email){
        return match(email, EMAIL_REGEX);
    }

    /**
     * 是否是有效的验证码格式
     * @param code 要校验的验证码
     * @return true:有效，false：无效
     */
    public static boolean isVerifyCode(String code){
        return match(code, VERIFY_CODE_REGEX);
    }

    /**
     * 校验是否符合正则格式
     * @param str
     * @param regex
     * @return
     */
    private static boolean match(String str, String regex){
        if (StrUtil.isBlank(str)) {
            return false;
        }
        return str.matches(regex);
    }

}
