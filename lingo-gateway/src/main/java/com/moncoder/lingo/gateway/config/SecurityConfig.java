package com.moncoder.lingo.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.moncoder.lingo.common.constant.AuthConstant;
import com.moncoder.lingo.gateway.component.RestfulAccessDeniedHandler;
import com.moncoder.lingo.gateway.component.RestfulAuthenticationEntryPoint;
import com.moncoder.lingo.gateway.component.SecurityWhiteUrls;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.AndServerWebExchangeMatcher;
import reactor.core.publisher.Mono;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO 全局安全配置
 * @date 2024/3/22 10:46
 */
//@AllArgsConstructor
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//
//    private final AuthorizationManager authorizationManager;
//    private final SecurityWhiteUrls securityWhiteUrls;
//    private final RestfulAccessDeniedHandler restfulAccessDeniedHandler;
//    private final RestfulAuthenticationEntryPoint restAuthenticationEntryPoint;
//    private final IgnoreUrlsRemoveJwtFilter ignoreUrlsRemoveJwtFilter;
//
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.
//        http.oauth2ResourceServer().jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter());
//        // 自定义处理JWT请求头过期或签名错误的结果
//        http.oauth2ResourceServer().authenticationEntryPoint(restAuthenticationEntryPoint);
//        // 对白名单路径，直接移除JWT请求头
//        http.addFilterBefore(ignoreUrlsRemoveJwtFilter, SecurityWebFiltersOrder.AUTHENTICATION);
//        http.authorizeExchange()
//                .pathMatchers(ArrayUtil.toArray(securityWhiteUrls.getUrls(),String.class)).permitAll()//白名单配置
//                .anyExchange().access(authorizationManager)// 鉴权管理器配置
//                .and().exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)// 处理未授权
//                .authenticationEntryPoint(restAuthenticationEntryPoint)// 处理未认证
//                .and().csrf().disable();
//        return http.build();
//    }
//
//
//}
