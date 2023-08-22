package com.example.demo.controllers;

import com.example.demo.crud.BookRepository;
import com.example.demo.crud.UserRepository;
import com.example.demo.data.Book;
import com.example.demo.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@Slf4j
public class AccountController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;



    @Autowired
    public AccountController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/account")
    public String get(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("books", user.getBooks());
        log.info("user books: " + user.getBooks().size());
        log.info(user.getUsername());
        log.info(user.getEmail());
        log.info(user.getPassword());
        return "account";
    }
}
