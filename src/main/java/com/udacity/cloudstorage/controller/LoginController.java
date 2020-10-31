package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("user") User user, Model model){
        return "login";
    }

    @PostMapping("/login")
    public void postCredentials(@ModelAttribute("user") User user, Model model){
        System.out.println(user.getUserName());
    }

}
