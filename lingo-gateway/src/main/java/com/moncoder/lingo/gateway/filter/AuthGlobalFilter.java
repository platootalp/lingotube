package com.moncoder.lingo.gateway.filter;


import cn.hutool.core.collection.CollUtil;
import com.moncoder.lingo.common.api.ResultCode;
import com.moncoder.lingo.common.constant.AuthConstant;
import com.moncoder.lingo.common.exception.UnauthorizedException;
import com.moncoder.lingo.gateway.component.AuthProperties;
import com.moncoder.lingo.gateway.util.JwtTool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Moncoder
 * @version 1.0
 * @description 自定义认证全局过滤器
 * @date 2023/11/23 14:45
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AuthProperties.class)
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final JwtTool jwtTool;

    private final AuthProperties authProperties;

    private final AntPathMatcher antPathMatcher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.允许跨域
        ServerHttpResponse response = exchange.getResponse();
        HttpHeaders httpHeaders = response.getHeaders();
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, PUT, POST, DELETE, OPTIONS");
        httpHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, Authorization");

        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod() == HttpMethod.OPTIONS) {
            response.setStatusCode(HttpStatus.OK);
            return Mono.empty();
        }
        // 2.判断是否不需要拦截
        if (isExclude(request.getPath().toString())) {
            // 无需拦截，直接放行
            return chain.filter(exchange);
        }
        // 3.获取请求头中的token
        String token = null;
        List<String> headers = request.getHeaders().get(AuthConstant.JWT_TOKEN_HEADER);
        if (!CollUtil.isEmpty(headers)) {
            token = headers.get(0).substring(AuthConstant.JWT_TOKEN_PREFIX.length());
        }
        log.debug("token: " + token);
        // 4.校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            // 如果无效，拦截
            response.setRawStatusCode(ResultCode.UNAUTHORIZED.getCode());
            log.debug("无效的token");
            return response.setComplete();
        }
        // 5.如果有效，传递用户信息
        String finalUserId = userId.toString();
        ServerWebExchange ex = exchange.mutate()
                .request(builder -> builder.header(AuthConstant.USER_TOKEN_HEADER, finalUserId))
                .build();
        log.debug(AuthConstant.USER_TOKEN_HEADER + ": " + finalUserId);
        // 6.放行
        return chain.filter(ex);
    }

    private boolean isExclude(String antPath) {
        for (String pathPattern : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(pathPattern, antPath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
