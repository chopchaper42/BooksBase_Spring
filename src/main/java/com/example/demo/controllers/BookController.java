package com.example.demo.controllers;

import com.example.demo.crud.BookRepository;
import com.example.demo.data.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String book(@RequestParam int id, Model model) {
        model.addAttribute(
                "book",
                bookRepository.findBookById(id).orElse(null)
        );
        return "book";
    }
}
