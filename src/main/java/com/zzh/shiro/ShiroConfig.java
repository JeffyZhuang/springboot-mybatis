package com.zzh.shiro;

import com.zzh.service.UserService;
import com.zzh.service.impl.UserServiceImpl;
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

    //设置默认的过滤接口，一般不可修改，放在DB的接口限制之后
    static {
        FILTERRULEMAP.put("/zzh/login", "anon");
        FILTERRULEMAP.put("/zzh/register", "anon");
        FILTERRULEMAP.put("/zzh/active", "anon");
        FILTERRULEMAP.put("/zzh/user/logout", "anon");
        FILTERRULEMAP.put("/401", "anon");
        FILTERRULEMAP.put("/404", "anon");
        FILTERRULEMAP.put("/**", "roles[admin]");
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
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
        loadDefaultAndDbFilterChain(factoryBean);
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("roles", new MyAuthorizationFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");

        return factoryBean;
    }

    private void loadDefaultAndDbFilterChain(ShiroFilterFactoryBean factoryBean) {
        //先将用户角色数据库中对应的权限加入到过滤器中
        Map<String, String> filterChainMap = new HashMap<>();
        Map<String, String> dbFilterPremission = userService().getRules();
        filterChainMap.putAll(dbFilterPremission);
        filterChainMap.putAll(FILTERRULEMAP);
        factoryBean.setFilterChainDefinitionMap(filterChainMap);
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
