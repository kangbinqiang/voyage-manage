package com.manage.config;

import com.manage.common.MangeRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 登录的url
//        shiroFilterFactoryBean.setLoginUrl("/login");
//        // 登录成功后跳转的url
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        // 未授权url
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

//        // 定义filterChain，静态资源不拦截
//        filterChainDefinitionMap.put("/css/**", "anon");
//        filterChainDefinitionMap.put("/js/**", "anon");
//        filterChainDefinitionMap.put("/fonts/**", "anon");
//        filterChainDefinitionMap.put("/img/**", "anon");
//        // druid数据源监控页面不拦截
//        filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");
//        // 配置退出过滤器，其中具体的退出代码Shiro已经替我们实现了
//        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "anon");
//        // 除上以外所有url都必须认证通过才可以访问，未通过认证自动访问LoginUrl
////        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean("securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(mangeRealm());
//        //关闭shiro自带的token
//        DefaultSubjectDAO defaultSubjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        defaultSubjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        securityManager.setSubjectDAO(defaultSubjectDAO);
        return securityManager;
    }


//    @Bean
//    @DependsOn("lifecycleBeanPostProcessor")
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
//        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
//        return defaultAdvisorAutoProxyCreator;
//    }
//
//    @Bean
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }
//
//    @Bean
//    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
//        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
//        advisor.setSecurityManager(securityManager);
//        return advisor;
//    }


    @Bean
    public MangeRealm mangeRealm() {
        MangeRealm mangeRealm = new MangeRealm();
        mangeRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return mangeRealm;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");  //MD5散列加密
        hashedCredentialsMatcher.setHashIterations(2);   //散列2次
        return hashedCredentialsMatcher;
    }
}