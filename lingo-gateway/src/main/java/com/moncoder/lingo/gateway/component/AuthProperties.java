package com.moncoder.lingo.gateway.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 认证路径名单
 * @date 2024/3/22 11:40
 */
@Data
@ConfigurationProperties(prefix = "lingo.tube.auth")
public class AuthProperties {
    private List<String> includePaths;
    private List<String> excludePaths;
}
