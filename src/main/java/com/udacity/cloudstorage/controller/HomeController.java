package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.Credential;
import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.service.ContentService;
import com.udacity.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {

    private UserService userService;
    private ContentService contentService;

    public HomeController(UserService userService, ContentService contentService) {
        this.userService = userService;
        this.contentService = contentService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);

        model.addAttribute("newNote", new Note());
        model.addAttribute("newCredentials", new Credential());
        model.addAttribute("notes", this.contentService.getNotesByUserId(user.getUserId()));
        model.addAttribute("files", this.contentService.getFilesByUserId(user.getUserId()));
        model.addAttribute("credentials", this.contentService.getCredentialsByUserId(user.getUserId()));
        return "home";
    }

    @PostMapping("/home/notes")
    public String saveNote(Authentication authentication, @ModelAttribute("newNote") Note note, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        this.contentService.addNote(user, note);
        note.setNoteDescription("");
        note.setNoteTitle("");

        return getHomePage(authentication, model);
    }

    @PostMapping("/home/files")
    public String saveFile(Authentication authentication, @RequestParam("fileUpload") MultipartFile multipartFile, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        this.contentService.addFile(user, multipartFile);

        return getHomePage(authentication, model);
    }
}
