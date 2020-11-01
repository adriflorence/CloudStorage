package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.File;
import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    private final NoteMapper noteMapper;
    private final FileMapper fileMapper;

    public ContentService(NoteMapper noteMapper, FileMapper fileMapper) {
        this.noteMapper = noteMapper;
        this.fileMapper = fileMapper;
    }

    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    public List<Note> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public int addNote(User user, Note note) {
        return noteMapper.insert(
            new Note(
                null,
                note.getNoteTitle(),
                note.getNoteDescription(),
                user.getUserId()
            )
        );
    }
}
