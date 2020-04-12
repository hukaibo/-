package com.example.demo.manager;

import com.example.demo.converter.p2c.UserInfoP2CConverter;
import com.example.demo.dao.UserInfoDAO;
import com.example.demo.exception.InvalidParameterException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.common.UserInfo;
import com.example.demo.model.persistence.UserInfoBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserInfoManagerImpl implements UserInfoManager {
    public static final int HASH_ITERATIONS=1000;
    private UserInfoDAO userInfoDAO;
    private UserInfoP2CConverter userInfoP2CConverter;

    public UserInfoManagerImpl(UserInfoDAO userInfoDAO, UserInfoP2CConverter userInfoP2CConverter) {
        this.userInfoDAO = userInfoDAO;
        this.userInfoP2CConverter = userInfoP2CConverter;
    }

    @Override
    public UserInfo getUserByUserId(Long userId) {
        Optional.ofNullable(userInfoDAO.getUserInfoById(userId))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("User %s was not found", userId)));
        com.example.demo.model.persistence.UserInfo userInfoById = userInfoDAO.getUserInfoById(userId);
        //com.example.demo.model.persistence.UserInfo convert1 = userInfoP2CConverter.reverse().convert(convert);
        return userInfoP2CConverter.convert(userInfoById);
    }

    @Override
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            return "login success";

    }

    @Override
    public UserInfo getUserByUsername(String username) {
        Optional.ofNullable(userInfoDAO.getUserByUsername(username))
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Username %s was not found", username)));
        com.example.demo.model.persistence.UserInfo userByUsername = userInfoDAO.getUserByUsername(username);
        return userInfoP2CConverter.convert(userByUsername);
    }

    @Override
    public UserInfo register(String username, String password) {
        com.example.demo.model.persistence.UserInfo userInfo = userInfoDAO.getUserByUsername(username);
        if (userInfo!=null){
          throw new InvalidParameterException(String.format("The user %s was register",username));
        }
        //Set random salt

        String salt= UUID.randomUUID().toString();
        Sha256Hash sha256Hash = new Sha256Hash(password,salt,HASH_ITERATIONS);
        String saltPassword = sha256Hash.toBase64();
        com.example.demo.model.persistence.UserInfo build = UserInfoBuilder.anUserInfo()
                .withUsername(username)
                .withPassword(saltPassword)
                .withSalt(salt)
                .withCreateTime(LocalDate.now())
                .build();
        userInfoDAO.createUser(build);
        Long idByUsername = userInfoDAO.getIdByUsername(username);
        build.setId(idByUsername);
        return userInfoP2CConverter.convert(build);

    }
}
