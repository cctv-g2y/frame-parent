package com.makun.config.session;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.stereotype.Component;

/**
 * @说明：session 过滤--访问控制过滤器
 * @author makun
 */
@Component
public class SessionConfig extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object object)
            throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject.isAuthenticated()) {
            return true;
        }
        redirectToLogin(request, response);
        return false;
    }

}
