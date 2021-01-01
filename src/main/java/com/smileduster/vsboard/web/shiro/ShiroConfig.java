package com.smileduster.vsboard.web.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, String> defMap = new LinkedHashMap<>();
        defMap.put("/test/hello-user", "authc");
        defMap.put("/member/**", "authc");
        defMap.put("/group/**", "authc");
        defMap.put("/battle/**", "authc");

        defMap.put("/**", "anon");
        bean.setFilterChainDefinitionMap(defMap);
        return bean;
    }

    @Bean
    public UserPwdRealm userPwdRealm() {
        UserPwdRealm realm = new UserPwdRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("sha-256");
        matcher.setHashIterations(1024);
        matcher.setStoredCredentialsHexEncoded(false);
        realm.setCredentialsMatcher(matcher);
        return realm;
    }

    @Bean
    public SecurityManager securityManager(UserPwdRealm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
