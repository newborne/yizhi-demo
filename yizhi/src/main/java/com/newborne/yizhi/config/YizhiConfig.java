package com.newborne.yizhi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * The type Yizhi config.
 */
@Configuration
@MapperScan("com.newborne.yizhi.mapper")
public class YizhiConfig {
    /**
     * Pagination interceptor pagination interceptor.
     *
     * @return the pagination interceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
