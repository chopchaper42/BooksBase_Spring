package com.example.demo.controllers;

import com.example.demo.utilities.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping
    public String get() {
        return "login";
    }
}
