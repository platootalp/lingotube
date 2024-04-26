package com.moncoder.lingo.admin.config;

import com.moncoder.lingo.common.constant.AuthConstant;
import com.moncoder.lingo.common.util.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

/**
 * @author Moncoder
 * @version 1.0
 * @description feign配置
 * @date 2024/3/29 23:29
 */
@EnableFeignClients()
public class DefaultFeignConfig {

    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return template -> {
            // 获取登录用户
            Integer userId = UserContext.getUser();
            if (userId == null) {
                // 如果为空则直接跳过
                return;
            }
            // 如果不为空则放入请求头中，传递给下游微服务
            template.header(AuthConstant.USER_TOKEN_HEADER, userId.toString());
        };
    }

    @Bean
    public SpringEncoder springEncoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new SpringEncoder(messageConverters);
    }
}
