package com.example.demo;

import com.example.demo.crud.UserRepository;
import com.example.demo.data.User;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @ModelAttribute
    public LoginForm loginForm() {
        return new LoginForm();
    }

    @GetMapping
    public String get() {
        return "login";
    }

    @PostMapping
    public String processLogin(LoginForm loginForm, PasswordEncoder passwordEncoder, Errors errors) {
        // check if usr exists
        // check if pass is good
        // login
        User user;

        if ((user = userRepository.findByUsername(loginForm.getUsername())) == null) {
            errors.rejectValue("username", "1", "User with such username doesn't exist.");
            return "login";
        }

        if (!user.getPassword().equals(passwordEncoder.encode(loginForm.getPassword()))) {
            errors.rejectValue("password", "1", "Wrong password.");
            return "login";
        }



    }
}
