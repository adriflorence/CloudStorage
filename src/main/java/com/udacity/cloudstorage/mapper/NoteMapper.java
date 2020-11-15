package com.udacity.cloudstorage.mapper;

import com.udacity.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("SELECT * FROM NOTES")
    List<Note> getAllNotes();

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getNotesByUserId(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE NOTES SET NOTETITLE = #{noteTitle}, NOTEDESCRIPTION = #{noteDescription}")
    void update(Integer noteId);

    @Delete("DELETE FROM NOTES WHERE NOTEID = #{noteId}")
    void delete(Integer noteId);
}
