package com.zzh.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zzh
 * @Description:Shiro配置文件
 * @Date: 2018/12/8
 */
@Slf4j
@Configuration
public class ShiroConfig {

    public static final Map<String, String> FILTERRULEMAP = new HashMap<>();

    static {
        FILTERRULEMAP.put("/zzh/user/login", "anon");
        FILTERRULEMAP.put("/zzh/user/logout", "anon");
        FILTERRULEMAP.put("/401", "anon");
        FILTERRULEMAP.put("/404", "anon");
        FILTERRULEMAP.put("/**", "roles[admin]");
        FILTERRULEMAP.put("/zzh/**", "authc");
    }

    @Bean(name = "myShiroRealm")
    public MyShiroRealm myShiroRealm() {
        return new MyShiroRealm();
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager getManager(SessionManager sessionManager, MyShiroRealm realm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(realm);
        manager.setSessionManager(sessionManager);
        return manager;
    }

    @Bean
    public SessionManager sessionManager() {
        MySessionManage mySessionManage = new MySessionManage();
        mySessionManage.setGlobalSessionTimeout(30 * 60 * 1000);
        return mySessionManage;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean factory(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        factoryBean.setFilterChainDefinitionMap(FILTERRULEMAP);
        filterMap.put("roles", new MyAuthorizationFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");

        return factoryBean;
    }


    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager
                                                                                               securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
