package com.moncoder.lingo.user.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/3/29 23:29
 */
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.FULL;
    }
}
