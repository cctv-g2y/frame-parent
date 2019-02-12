package com.makun.config.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;

/**
 * @说明：MybatisPlus配置
 * @author: makun
 */
@Configuration
@MapperScan(basePackages = { "com.makun.dao.*" })
public class MybatisPlusConfig {

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);// 开启 PageHelper 的支持
        paginationInterceptor.setDialectType("mysql");
        return paginationInterceptor;
    }

//    /**
//     * mybatis分页插件
//     */
//    @Bean
//    public PageHelper getpageHelper() {
//        return new PageHelper();
//    }

}
