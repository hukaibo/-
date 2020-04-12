package com.example.demo.config;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.common.UserInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRealm extends AuthorizingRealm {
    private UserInfoManager userInfoManager;


    @Autowired
    public UserRealm(UserInfoManager userInfoManager,HashedCredentialsMatcher matcher) {
        super(matcher);
        this.userInfoManager = userInfoManager;
    }




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserInfo userByUsername = userInfoManager.getUserByUsername(username);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                username,
                userByUsername.getPassword(),
                ByteSource.Util.bytes(userByUsername.getSalt()),
                getName());
        return authenticationInfo;
    }
}
