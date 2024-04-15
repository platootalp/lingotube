package com.moncoder.lingo.video.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.moncoder.lingo.video.component.OssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moncoder
 * @version 1.0
 * @description 阿里云对象存储配置
 * @date 2024/4/15 21:05
 */
@Configuration
public class OssConfig {

    @Autowired
    private OssProperties ossProperties;

    @Bean
    @ConditionalOnMissingBean
    public OSS oss() {
        return new OSSClientBuilder().build(ossProperties.getEndpoint(),
                ossProperties.getAccessKeyId(), ossProperties.getAccessKeySecret());
    }

}
