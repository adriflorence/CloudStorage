package com.udacity.cloudstorage.service;
import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
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
