package com.moncoder.lingo.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.moncoder.lingo.common.constant.AuthConstant;
import com.moncoder.lingo.common.util.UserContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Moncoder
 * @version 1.0
 * @description 用户信息拦截器
 * @date 2024/4/17 16:12
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1.获取请求头中的用户信息
        String userId = request.getHeader(AuthConstant.USER_TOKEN_HEADER);
        // 2.判断是否为空
        if (StrUtil.isNotBlank(userId)) {
            // 不为空，保存到redis中
            UserContext.setUser(Integer.valueOf(userId));
        }
        // 3.放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 移除用户
        UserContext.removeUser();
    }
}
