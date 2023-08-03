package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String processSignup() {
        return "blank"; // TODO: do something here
    }
}
