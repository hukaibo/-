package com.example.demo.controller;

import com.example.demo.converter.c2s.UserInfoC2SConverter;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private UserInfoManager userInfoManager;
    private UserInfoC2SConverter userInfoC2SConverter;

    @Autowired
    public UserController(UserInfoManager userInfoManager, UserInfoC2SConverter userInfoC2SConverter) {
        this.userInfoManager = userInfoManager;
        this.userInfoC2SConverter = userInfoC2SConverter;
    }

    @GetMapping("/{id}")
    public UserInfo getUserInfoByUserId(@PathVariable("id") Long userId) {
        com.example.demo.model.common.UserInfo userInfo = userInfoManager.getUserByUserId(userId);
        return userInfoC2SConverter.convert(userInfo);
    }
}
