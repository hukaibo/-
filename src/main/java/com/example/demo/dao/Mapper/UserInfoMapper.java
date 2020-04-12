package com.example.demo.dao.Mapper;

import com.example.demo.model.persistence.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

@Mapper
public interface UserInfoMapper {
    @Select("select id,username,password,create_time,update_time from hcas_userinfo where id=#{id}")
    UserInfo getUserInfoByUserId(@Param("id") Long id);

    @Select("select id,username,password,create_time,update_time,salt from hcas_userinfo where username=#{username}")
    UserInfo getUserByUsername(@Param("username") String username);

    @Insert("insert into hcas_userinfo(username,password,salt,create_time)" +
            "values(#{username},#{password},#{salt},#{createTime})")
    void createUser(UserInfo userInfo);
    @Select("select id from hcas_userinfo where username=#{username}")
    Long getUserIdByUsername(@Param("username") String username);

}
