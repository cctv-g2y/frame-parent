package com.makun.actutils;

import org.activiti.engine.ProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @说明:修改activiti流程图的字体,防止中文乱码
 */
@Configuration
public class SelfActivitiConfig {

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Bean
    public ProcessEngineConfiguration setMyself() {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        return this.processEngineConfiguration;
    }

}
