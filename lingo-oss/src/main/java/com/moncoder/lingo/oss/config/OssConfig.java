package com.moncoder.lingo.oss.config;

import com.moncoder.lingo.oss.util.OssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云存储服务配置
 * @date 2024/4/15 21:05
 */
@Configuration
public class OssConfig {

    @Bean
    @ConditionalOnMissingBean
    public OssUtil ossUtil() {
        return new OssUtil();
    }

}
