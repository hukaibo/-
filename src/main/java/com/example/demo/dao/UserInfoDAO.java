package com.example.demo.dao;

import com.example.demo.model.persistence.UserInfo;

public interface UserInfoDAO {
    UserInfo getUserInfoById(Long id);

    UserInfo getUserByUsername(String username);

    void createUser(UserInfo userInfo);

    Long getIdByUsername(String username);
}
