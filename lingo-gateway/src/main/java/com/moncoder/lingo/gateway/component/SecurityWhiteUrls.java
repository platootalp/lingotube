package com.moncoder.lingo.gateway.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 安全白名单路径
 * @date 2024/3/22 11:19
 */
@Getter
@Setter
@Component
@ConfigurationProperties("security.white.urls")
public class SecurityWhiteUrls {

    private List<String> urls = new ArrayList<>();
}
