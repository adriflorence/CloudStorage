package com.udacity.cloudstorage.controller;

import com.udacity.cloudstorage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // @RequestMapping binds the controller to a specific request URL
    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute("user") User user, Model model){
        return "login";
    }

    @PostMapping("/login")
    public void postCredentials(Authentication authentication, Model model){
        String username = (String) authentication.getPrincipal();
        // TODO check credentials
        model.addAttribute("invalidCredentials", true);
    }

    @PostMapping("/logout")
    public String logout(Model model){
        model.addAttribute("loggedOut", true);
        return "login";
    }

}
