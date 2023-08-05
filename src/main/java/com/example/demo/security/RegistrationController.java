package com.example.demo.security;

import com.example.demo.RegistrationForm;
import com.example.demo.crud.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm regForm, Errors errors) {
        boolean error = false;

        if (userRepository.findByUsername(regForm.getUsername()) != null) {
            error = true;
            errors.rejectValue("username", "1", "Username already exists.");
        }
        if (userRepository.findByEmail(regForm.getEmail()) != null) {
            error = true;
            errors.rejectValue("email", "1", "Email already in use.");
        }
        if (regForm.getPassword() != regForm.getPasswordConfirmation()) {
            error = true;
            errors.rejectValue("passwordConfirmation", "1", "Passwords should match.");
        }
        if (errors.hasErrors() || error) {
            log.info("errors detected.");
            return "registration";
        }

        userRepository.save(regForm.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
