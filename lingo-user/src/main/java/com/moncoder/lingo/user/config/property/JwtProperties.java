package com.moncoder.lingo.user.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.time.Duration;

/**
 * @author Moncoder
 * @version 1.0
 * @description JWT参数
 * @date 2024/3/22 11:40
 */
@Data
@ConfigurationProperties(prefix = "lingo.tube.jwt")
public class JwtProperties {
    private Resource location;
    private String password;
    private String alias;
    private Duration tokenTTL = Duration.ofMinutes(10);
}
