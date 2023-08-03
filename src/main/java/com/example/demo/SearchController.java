package com.example.demo;

import com.example.demo.crud.BookRepository;
import com.example.demo.data.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/search")
public class SearchController {

    private BookRepository bookRepository;

    @Autowired
    public SearchController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*@GetMapping
    public String search(Model model) {
        return "search";
    }*/

    @GetMapping
    public String processSearch(@RequestParam String value, Model model) {
        List<Book> booksByTitle = bookRepository.findBookByTitleContainingIgnoreCase(value);
        log.info("books by title like _ size: " + booksByTitle.size());
        model.addAttribute("books", booksByTitle);
        return "search";
    }
}
