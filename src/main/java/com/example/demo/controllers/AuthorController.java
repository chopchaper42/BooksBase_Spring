package com.example.demo.controllers;

import com.example.demo.crud.AuthorRepository;
import com.example.demo.data.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
//@EnableJpaRepositories("com.example.demo.crud")
public class AuthorController {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public String author(@RequestParam int id, Model model) {
        model.addAttribute("author", authorRepository.getAuthorById(id));
        return "author";
    }
}
