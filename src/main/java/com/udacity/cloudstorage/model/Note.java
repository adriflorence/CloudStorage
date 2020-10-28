package com.udacity.cloudstorage.model;

public class Note {

    private int noteId;
    private String noteTitle;
    private String noteDescription;

    public Note(int noteId, String noteTitle, String noteDescription) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }
}
