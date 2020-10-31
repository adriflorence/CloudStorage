package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USERS WHERE USERNAME = #{userName}")
    User getUser(String userName); // method names are arbitrary

    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{userName}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insert(User user);

    @Update("UPDATE USERS SET USERNAME = #{userName}, SALT = #{salt}, PASSWORD = #{password}, FIRSTNAME = #{firstName}, LASTNAME = #{lastName} WHERE USERID = #{userId}")
    void update(Integer userId);

    @Delete("DELETE * FROM USERS WHERE USERID = #{userId}")
    void delete(Integer userId);
}
