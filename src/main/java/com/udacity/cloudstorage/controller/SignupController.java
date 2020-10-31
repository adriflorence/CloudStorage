package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import com.udacity.cloudstorage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage(@ModelAttribute("user") User user, Model model){
        return "signup";
    }

    @PostMapping
    public String postCredentials(@ModelAttribute("user") User user, Model model){
        String signupError = null;

        // check if username already exists in DB
        if (!userService.isUsernameAvailable(user.getUserName())) {
            signupError = "This username already exists.";
        }

        if(signupError == null) {
            int usersCreated = userService.createUser(user);
            if (usersCreated < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
