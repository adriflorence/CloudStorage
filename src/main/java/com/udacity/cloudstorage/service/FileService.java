package com.udacity.cloudstorage.service;

import com.udacity.cloudstorage.mapper.FileMapper;
import com.udacity.cloudstorage.model.File;
import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getFilesByUserId(Integer userId) {
        return fileMapper.getFilesByUserId(userId);
    }

    public File getFileById(Integer fileId) {
        return fileMapper.getFileById(fileId);
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

    public void deleteFile(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
