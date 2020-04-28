package com.example.demo.controller;

import com.example.demo.converter.c2s.UserInfoC2SConverter;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.InvalidParameterException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ServiceException;
import com.example.demo.manager.UserInfoManager;
import com.example.demo.model.common.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1.0/users")
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
        if (userId == null || userId <= 0) {
            throw new InvalidParameterException(String.format("User id %s is invalid", userId));
        }
        com.example.demo.model.common.UserInfo userInfo = userInfoManager.getUserByUserId(userId);
        return ResponseEntity.ok(Objects.requireNonNull(userInfoC2SConverter.convert(userInfo)));
    }
//consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
//produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
    @PostMapping(produces ="application/json",consumes = "application/json")
    public ResponseEntity<?>  register(@RequestBody com.example.demo.model.service.UserInfo userInfo
                                       ) {
        String username = userInfo.getUsername();
        String password = userInfo.getPassword();
        UserInfo register = userInfoManager.register(username, password);
        return ResponseEntity.ok(Objects.requireNonNull(userInfoC2SConverter.convert(register)));
    }
}
