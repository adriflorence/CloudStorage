package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.Note;
import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, @ModelAttribute("newNote") Note note, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        model.addAttribute("notes", this.userService.getNotesByUserId(user.getUserId()));
        return "home";
    }

    @PostMapping("/notes")
    public String saveNote(@ModelAttribute("newNote") Note note, Authentication authentication, Model model){
        String username = (String) authentication.getPrincipal();
        User user = this.userService.getUser(username);
        this.userService.addNote(user, note);
        note.setNoteDescription("");
        note.setNoteTitle("");
        model.addAttribute("notes", this.userService.getNotesByUserId(user.getUserId()));
        return "home";
    }
}
