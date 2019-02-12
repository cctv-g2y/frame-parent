//package com.makun.config.webfilter;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
///**
// * @说明：[拦截器配置]配置了shiro拦截器，此拦截器可不要，先保留
// * 
// * @author: makun
// */
//@Configuration
//public class MyWebMvcConfig extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    private MyInterceptor myInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用于排除拦截--放行登录方法
//        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/init");
//        super.addInterceptors(registry);
//    }
//
//}
