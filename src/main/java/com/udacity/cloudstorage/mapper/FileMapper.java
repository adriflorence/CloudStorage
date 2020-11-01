package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FileMapper {

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFilesByUserId(Integer userId);

    @Insert("INSERT INTO NOTES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

}
