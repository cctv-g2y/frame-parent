package com.makun.config.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @说明：servlet容器
 * @author makun
 */
@Configuration
public class ServletConfig {

    /**
     * testServlet
     */
    @Bean
    public ServletRegistrationBean testServlet() {
        return new ServletRegistrationBean(new TestServlet(), "/test");
    }

}
