package com.example.demo.converter.c2s;

import com.example.demo.model.common.UserInfo;
import com.example.demo.model.service.UserInfoBuilder;
import com.google.common.base.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserInfoC2SConverter extends Converter<UserInfo, com.example.demo.model.service.UserInfo> {

    @Override
    protected com.example.demo.model.service.UserInfo doForward(UserInfo userInfo) {
        return UserInfoBuilder.anUserInfo()
                .withId(userInfo.getId())
                .withUsername(userInfo.getUsername())
                .withPassword(userInfo.getPassword())
                .build();
    }

    @Override
    protected UserInfo doBackward(com.example.demo.model.service.UserInfo userInfo) {
        return com.example.demo.model.common.UserInfoBuilder.anUserInfo()
                .withId(userInfo.getId())
                .withUsername(userInfo.getUsername())
                .withPassword(userInfo.getPassword())
                .build();
    }
}
