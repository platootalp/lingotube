package com.moncoder.lingo.video.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * @author Moncoder
 * @version 1.0
 * @description Kafka配置
 * @date 2024/5/21 17:09
 */
@Configuration
public class KafkaConfig {

    /**
     * JSON消息转换器
     */
    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }
}
