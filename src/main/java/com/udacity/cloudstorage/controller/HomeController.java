package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.Credential;
import com.udacity.cloudstorage.model.File;
import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.service.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class HomeController {

    private UserService userService;
    private NoteService noteService;
    private FileService fileService;
    private CredentialService credentialService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NoteService noteService, FileService fileService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);

        model.addAttribute("newNote", new Note());
        model.addAttribute("newCredential", new Credential());
        model.addAttribute("notes", this.noteService.getNotesByUserId(user.getUserId()));
        model.addAttribute("files", this.fileService.getFilesByUserId(user.getUserId()));
        model.addAttribute("credentials", this.credentialService.getCredentialsByUserId(user.getUserId()));
        model.addAttribute("encryptionService", encryptionService);
        return "home";
    }

    @PostMapping("/notes")
    public String saveNote(Authentication authentication, @ModelAttribute("newNote") Note note, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        if(note.getNoteId() == null) {
            this.noteService.addNote(user, note);
        } else {
            this.noteService.updateNote(user, note);
        }
        note.setNoteDescription("");
        note.setNoteTitle("");

        return getHomePage(authentication, model);
    }

    @PostMapping("/notes/delete/{noteId}")
    public String deleteNote(Authentication authentication, @PathVariable Integer noteId, Model model){
        noteService.deleteNote(noteId);

        return getHomePage(authentication, model);
    }

    @PostMapping("/files")
    public String saveFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        this.fileService.addFile(user, multipartFile);

        return getHomePage(authentication, model);
    }

    @PostMapping("/files/delete/{fileId}")
    public String deleteFile(Authentication authentication, @PathVariable Integer fileId, Model model){
        fileService.deleteFile(fileId);

        return getHomePage(authentication, model);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> download(@PathVariable("fileId") Integer fileId) throws IOException {
        File file = fileService.getFileById(fileId);
        ByteArrayResource resource = new ByteArrayResource(file.getFileData());

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getFileName());
        header.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(Integer.valueOf(file.getFileSize()))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @PostMapping("/credentials")
    public String saveCredential(Authentication authentication, @ModelAttribute("newCredential") Credential credential, Model model) {
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        this.credentialService.addCredential(user, credential);

        return getHomePage(authentication, model);
    }
}
