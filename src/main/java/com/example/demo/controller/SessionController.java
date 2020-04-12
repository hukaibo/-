package com.example.demo.controller;

import com.example.demo.manager.UserInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1.0/session")
public class SessionController {

    private UserInfoManager userInfoManager;

    @Autowired
    public SessionController(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @PostMapping
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
         return userInfoManager.login(username,password);
    }
}
