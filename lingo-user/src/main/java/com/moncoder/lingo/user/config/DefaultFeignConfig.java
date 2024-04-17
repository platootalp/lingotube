package com.moncoder.lingo.user.config;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
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
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
