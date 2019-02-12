package com.makun.config.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.makun.config.oauth2.OAuth2Realm;
import com.makun.config.session.SessionConfig;
import com.makun.config.shiroredis.ShiroRedisCacheManager;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * @说明：shiro 配置
 * @author makun
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SessionDAO sessionDAO() {
        MemorySessionDAO sessionDAO = new MemorySessionDAO();
        sessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return sessionDAO;
    }

    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setDeleteInvalidSessions(true);
        // 设置Session过期时间，以毫秒为单位
        // sessionManager.setGlobalSessionTimeout(1 * 60 * 1000);
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(oAuth2Realm);
        securityManager.setCacheManager(shiroRedisCacheManager());
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, SessionDAO sessionDAO) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 没有登陆的用户只能访问登陆页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面; ----这个配置了没卵用
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");
        // 自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        SessionConfig sessionConfig = new SessionConfig();
        filtersMap.put("oauth2", sessionConfig);
        shiroFilterFactoryBean.setFilters(filtersMap);
        // 权限控制map.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/bootstrap/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/jedate-6.5.0/**", "anon");
        filterChainDefinitionMap.put("/jquery-3.1.1/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        filterChainDefinitionMap.put("/login.html", "anon");
        filterChainDefinitionMap.put("/403.html", "anon");
        filterChainDefinitionMap.put("/init", "anon");
        filterChainDefinitionMap.put("/**", "oauth2");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 缓存管理器，缓存都从这里取
     */
    @Bean
    public ShiroRedisCacheManager shiroRedisCacheManager() {
        ShiroRedisCacheManager shiroRedisCacheManager = new ShiroRedisCacheManager();
        return shiroRedisCacheManager;
    }

}
