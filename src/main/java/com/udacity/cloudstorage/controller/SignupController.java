package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignupPage(@ModelAttribute("user") User user, Model model){
        return "signup";
    }

    @PostMapping("/signup")
    public void postCredentials(@ModelAttribute("user") User user, Model model){
        userService.createUser(user);

        // TODO add error handling
    }
}
