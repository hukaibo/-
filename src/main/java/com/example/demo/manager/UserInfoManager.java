package com.example.demo.manager;


import com.example.demo.model.common.UserInfo;
import org.springframework.stereotype.Component;

public interface UserInfoManager {
    /**
     * get userInformation by user id
     *
     * @param userId
     */
    UserInfo getUserByUserId(Long userId);

    /**
     * Login with username and password
     * @param username
     * @param password
     */
    String login(String username, String password);

    /**
     * get user by username
     * @param username
     * @return
     */
    UserInfo getUserByUsername(String username);
    /**
     * register new user with username and password
     * @param username username
     * @param password the related password
     */
    UserInfo register(String username,String password);
}
