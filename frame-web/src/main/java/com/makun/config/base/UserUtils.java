package com.makun.config.base;

import java.util.Map;

import org.apache.shiro.SecurityUtils;

import com.makun.entity.system.SysUser;

/**
 * @说明:用户工具类
 * @author: makun
 */
public class UserUtils {

    public static SysUser getUser() {
        // return (SysUser) request.getSession().getAttribute("user");
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getUserId() {
        return getUser().getUserId();
    }

    public static String getDeptId() {
        return getUser().getDeptId();
    }

    public static void initParams(Map<String, Object> params) {
        params.put("userId", getUserId());
    }

}
