//package com.makun.config.webfilter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.makun.entity.system.sysUser;
//import com.makun.utils.isEmptyObj;
//
///**
// * @说明：[处理拦截结果] @author: makun
// */
//@Component
//public class MyInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
//        sysUser user = (sysUser) request.getSession().getAttribute("user");
//        if (null == user || isEmptyObj.isempty(user)) {
//            response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
//                    + request.getContextPath() + "/login.html");
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//            ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//
//    }
//
//}
