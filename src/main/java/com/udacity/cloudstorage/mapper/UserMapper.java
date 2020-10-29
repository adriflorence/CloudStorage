package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE username = #{username}")
    User getUser(String username); // method names are arbitrary

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true)
    int insert(User user);

    @Update("UPDATE USERS SET USERNAME = #{username}, SALT = #{salt}, PASSWORD = #{password}, FIRSTNAME = #{firstname}, LASTNAME = #{lastname} WHERE USERID = #{userId}")
    void update(Integer userId);

    @Delete("DELETE * FROM USERS WHERE USERID = #{userId}")
    void delete(Integer userId);
}
