package com.moncoder.lingo.common.aspect;

import com.moncoder.lingo.common.exception.UnauthorizedException;
import com.moncoder.lingo.common.util.UserContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Moncoder
 * @version 1.0
 * @description 登陆验证切面
 * @date 2024/4/18 10:21
 */
@Component
@Aspect
public class LoginAspect {

    @Before("@annotation(com.moncoder.lingo.common.annotation.RequireLogin)")
    public void checkLogin() {
        // 检查用户是否登录的逻辑，如果未登录则抛出异常或进行其他处理
        if (UserContext.getUser() == null) {
            throw new UnauthorizedException();
        }
    }
}
