package com.moncoder.lingo.vocabulary.config;

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
@MapperScan({"com.moncoder.lingo.mapper"})
@Configuration
public class MybatisPlusConfig {
    /**
     * 分页拦截器
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 创建分页拦截器对象
        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        // 设置数据库类型，不同的数据库有不同的分页方式
        paginationInterceptor.setDbType(DbType.MYSQL);
        interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }
}
