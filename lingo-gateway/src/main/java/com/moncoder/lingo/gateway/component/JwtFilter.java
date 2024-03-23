package com.moncoder.lingo.gateway.component;

import cn.hutool.core.util.StrUtil;
import com.moncoder.lingo.common.constant.AuthConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.text.ParseException;


/**
 * @author Moncoder
 * @version 1.0
 * @description TODO JWT过滤器
 * @date 2024/3/22 11:36
 */
//@Slf4j
//@Component
//@Order(0)
//public class JwtFilter  implements GlobalFilter {
//
//
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        String token = exchange.getRequest().getHeaders().getFirst(AuthConstant.JWT_TOKEN_HEADER);
//        if (StrUtil.isEmpty(token)) {
//            return chain.filter(exchange);
//        }
//        try {
//            //从token中解析用户信息并设置到Header中去
//            String realToken = token.replace(AuthConstant.JWT_TOKEN_PREFIX, "");
//            JWSObject jwsObject = JWSObject.parse(realToken);
//            String userStr = jwsObject.getPayload().toString();
//            log.info("AuthGlobalFilter.filter() user:{}",userStr);
//            ServerHttpRequest request = exchange.getRequest().mutate()
//                    .header(AuthConstant.USER_TOKEN_HEADER, userStr).build();
//            exchange = exchange.mutate().request(request).build();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return chain.filter(exchange);
//    }
//
//}
