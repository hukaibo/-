package com.example.demo.config;


import com.example.demo.manager.UserInfoManager;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    @Autowired
    private UserInfoManager userInfoManager;


    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm);

        return defaultWebSecurityManager;

    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();
        //stringStringLinkedHashMap.put("v1.0/greeting", "authc");
        //stringStringLinkedHashMap.put("v1.0/users/", "anon");
        stringStringLinkedHashMap.put("/v1.0/users", "anon");
        stringStringLinkedHashMap.put("/v1.0/session", "anon");
        stringStringLinkedHashMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(stringStringLinkedHashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher matcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(1000);
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;
    }

}
