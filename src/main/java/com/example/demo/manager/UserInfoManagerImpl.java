package com.example.demo.manager;

import com.example.demo.converter.p2c.UserInfoP2CConverter;
import com.example.demo.dao.UserInfoDAO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.common.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    private UserInfoDAO userInfoDAO;
    private UserInfoP2CConverter userInfoP2CConverter;

    public UserInfoManagerImpl(UserInfoDAO userInfoDAO, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDAO = userInfoDAO;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserByUserId(Long userId) {
        Optional.ofNullable(userInfoDAO.getUserInfoById(userId))
                .orElseThrow(()->new ResourceNotFoundException(String.format("User %s was not found",userId)));
        com.example.demo.model.persistence.UserInfo userInfoById = userInfoDAO.getUserInfoById(userId);
        //com.example.demo.model.persistence.UserInfo convert1 = userInfoP2CConverter.reverse().convert(convert);
    return userInfoP2CConverter.convert(userInfoById);
    }
}
