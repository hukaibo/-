package com.example.demo.config;


import com.example.demo.manager.UserInfoManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    private UserInfoManager userInfoManager;


    @Bean
    public SecurityManager securityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager(realm);
        SecurityUtils.setSecurityManager(defaultWebSecurityManager);
        return defaultWebSecurityManager;

    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager
    ) {
        CustomShiroFilterFactoryBean shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //filters.put("custom", new CustomHttpFilter());
        //filters.put("authc", new CustomFormAuthenticationFilter());
        LinkedHashMap<String, String> shiroFilterDefinitionMap = new LinkedHashMap<>();
        shiroFilterDefinitionMap.put("/v1.0/users/**::POST", "authc");
        //"authc"表示需要认证才能使用
        //shiroFilterDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDefinitionMap);
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
