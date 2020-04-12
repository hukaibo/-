package com.example.demo.converter.p2c;

import com.example.demo.model.common.UserInfoBuilder;
import com.example.demo.model.persistence.UserInfo;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoP2CConverter extends Converter<UserInfo, com.example.demo.model.common.UserInfo> {
    @Override
    protected com.example.demo.model.common.UserInfo doForward(UserInfo userInfo) {
        return UserInfoBuilder.anUserInfo()
                .withId(userInfo.getId())
                .withUsername(userInfo.getUsername())
                .withPassword(userInfo.getPassword())
                .withSalt(userInfo.getSalt())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.example.demo.model.common.UserInfo userInfo) {
        return com.example.demo.model.persistence.UserInfoBuilder.anUserInfo()
                .withId(userInfo.getId())
                .withUsername(userInfo.getUsername())
                .withPassword(userInfo.getPassword())
                .build();
    }
}
