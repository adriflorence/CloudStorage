package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    // @RequestMapping binds the controller to a specific request URL
    @GetMapping
    public String getLoginPage(@ModelAttribute("user") User user, Model model){
        return "login";
    }

    @PostMapping("/login")
    public void postCredentials(@ModelAttribute("user") User user, Model model){
        System.out.println(user.getUserName());
    }

}
