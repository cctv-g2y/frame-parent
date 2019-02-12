package com.makun.config.base;

import org.springframework.stereotype.Component;

/**
 * @说明:导出workflow文件目录常量
 * @author makun
 *
 */
@Component("APP_PROP")
public class AppProp {

    private String activitiPath = "D:/backup";

    public String getActivitiPath() {
        return this.activitiPath;
    }

}
