package com.example.demo.controllers;

import com.example.demo.crud.BookRepository;
import com.example.demo.crud.UserRepository;
import com.example.demo.data.Book;
import com.example.demo.data.User;
import com.example.demo.utilities.UserBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
public class HomeController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserBookService userBookService;

    @Autowired
    public HomeController(BookRepository bookRepository, UserRepository userRepository, UserBookService userBookService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.userBookService = userBookService;
    }

    @GetMapping
    public String home(Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated())
            userBookService.initModelWithUserBooks(model, authentication);

        addTrendingBooksToModel(model);
        return "home";
    }

    @PostMapping("/addBook")
    public String processBookAdd(@RequestParam long bookId, Authentication authentication) {
        userBookService.addBookToUserBooks(bookId, authentication);
        return "redirect:/";
    }
    @PostMapping("/removeBook")
    public String processBookRemoval(@RequestParam long bookId, Authentication authentication) {
        userBookService.removeBookFromUserBooks(bookId, authentication);
        return "redirect:/";
    }

    private void addTrendingBooksToModel(Model model) {
        List<Book> books = bookRepository.getTrendingBooks();
        model.addAttribute("books", books);
    }
}
