package com.moncoder.lingo.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moncoder
 * @version 1.0
 * @description TODO
 * @date 2024/3/20 15:43
 */
@MapperScan("com.moncoder.lingo.mapper")
@Configuration
public class MybatisPlusConfig {
}
