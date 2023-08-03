package com.example.demo;

import com.example.demo.crud.BookRepository;
import com.example.demo.data.Book;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class HomeController {

    /*@GetMapping("/")
    public String home(Model model) {
        return "home";
    }*/
}
