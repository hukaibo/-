package com.example.demo.manager;


import com.example.demo.model.common.UserInfo;
import org.springframework.stereotype.Component;

public interface UserInfoManager {
    /**
     * get userInformation by user id
     * @param userId
     */
    UserInfo getUserByUserId(Long userId);
}
