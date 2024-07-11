package com.moncoder.lingo.user.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Moncoder
 * @version 1.0
 * @description 微信扫码参数
 * @date 2024/7/10 21:00
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {
   private String appId;
   private String appSecret;
   private String redirectUri;
}
