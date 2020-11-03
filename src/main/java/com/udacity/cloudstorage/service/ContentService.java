package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.CredentialMapper;
import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.mapper.NoteMapper;
import com.udacity.cloudstorage.model.Credential;
import com.udacity.cloudstorage.model.File;
import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ContentService {

    private final FileMapper fileMapper;
    private final NoteMapper noteMapper;
    private final CredentialMapper credentialMapper;

    public ContentService(FileMapper fileMapper, NoteMapper noteMapper, CredentialMapper credentialMapper) {
        this.fileMapper = fileMapper;
        this.noteMapper = noteMapper;
        this.credentialMapper = credentialMapper;
    }

    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    public List<Note> getNotesByUserId(Integer userId) {
        return noteMapper.getNotesByUserId(userId);
    }

    public List<Credential> getCredentialsByUserId(Integer userId) {
        return credentialMapper.getCredentialsByUserId(userId);
    }

    public int addFile(User user, MultipartFile file) {

        try {
            return fileMapper.insert(
                new File(
                    null,
                    file.getOriginalFilename(),
                    file.getContentType(),
                    String.valueOf(file.getSize()),
                    user.getUserId(),
                    file.getBytes()
                )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
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

    public int addCredentials(User user, Credential credential) {
        return credentialMapper.insert(
            new Credential(
                null,
                credential.getUrl(),
                user.getUserName(),
                credential.getKey(),
                credential.getPassword(),
                user.getUserId()
            )
        );
    }
}
