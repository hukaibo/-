package com.example.demo.controller;

import com.example.demo.converter.c2s.UserInfoC2SConverter;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.InvalidParameterException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.service.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

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
    public ResponseEntity<?> getUserInfoByUserId(@PathVariable("id") Long userId) {
        if (userId==null||userId<=0){
              throw new InvalidParameterException(String.format("User id %s is invalid",userId));
        }
            com.example.demo.model.common.UserInfo userInfo = userInfoManager.getUserByUserId(userId);
            return ResponseEntity.ok(Objects.requireNonNull(userInfoC2SConverter.convert(userInfo)));
    }
}
