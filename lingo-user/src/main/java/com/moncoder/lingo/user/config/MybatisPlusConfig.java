package com.moncoder.lingo.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Moncoder
 * @version 1.0
 * @description mybatis-plus配置
 * @date 2024/3/20 15:43
 */
@MapperScan("com.moncoder.lingo.mapper")
@Configuration
public class MybatisPlusConfig {
}
