package com.udacity.cloudstorage.model;

import java.sql.Blob;

public class File {

    private int fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Blob fileData;

    public File(int fileId, String fileName, String contentType, String fileSize, Blob fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
    }
}
