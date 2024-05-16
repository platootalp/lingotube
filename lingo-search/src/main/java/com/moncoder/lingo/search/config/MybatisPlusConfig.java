package com.moncoder.lingo.search.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Moncoder
 * @version 1.0
 * @description mybatis-plus配置
 * @date 2024/3/21 15:56
 */
@MapperScan({"com.moncoder.lingo.mapper", "com.moncoder.lingo.search.dao"})
@Configuration
public class MybatisPlusConfig {

}
