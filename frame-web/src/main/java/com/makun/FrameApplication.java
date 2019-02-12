package com.makun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @说明:启动类
 * @author makun
 */
@SpringBootApplication
//启注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@ServletComponentScan(basePackages = { "com.makun.servlet" })
@ComponentScan(basePackages = { "org.activiti.rest.editor.main", "org.activiti.rest.editor.model",
        "org.activiti.rest.diagram.services", "com.makun.**.*" })
public class FrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrameApplication.class, args);
    }

}
