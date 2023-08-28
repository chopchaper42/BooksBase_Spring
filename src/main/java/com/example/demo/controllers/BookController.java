package com.example.demo.controllers;

import com.example.demo.crud.BookRepository;
import com.example.demo.crud.ReviewRepository;
import com.example.demo.crud.UserRepository;
import com.example.demo.data.Book;
import com.example.demo.data.Review;
import com.example.demo.utilities.UserBookService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserBookService userBookService;

    @ModelAttribute(name = "book")
    public Book book() {
        return new Book();
    }

    public BookController(BookRepository bookRepository, UserRepository userRepository, ReviewRepository reviewRepository, UserBookService userBookService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.reviewRepository = reviewRepository;
        this.userBookService = userBookService;
    }

    @GetMapping
    public String book(@RequestParam int id, Model model, Authentication authentication) {
        Book book = bookRepository.findBookById(id);
        model.addAttribute("book", book);

        if (authentication != null && authentication.isAuthenticated()) {
            userBookService.initModelWithUserBooks(model, authentication);
            model.addAttribute("blank_review", new Review()); // fix: add even if unauthenticated
        }

        //load author's books
        List<Book> authorBooks = bookRepository.getBooksByAuthorAndIdNot(book.getAuthor(), book.getId());
        model.addAttribute("authorBooks", authorBooks);

        return "book";
    }

    @PostMapping("/addBook")
    public String addBookToFavorite(@RequestParam int bookId, Authentication authentication) {
        userBookService.addBookToUserBooks(bookId, authentication);

        return "redirect:/book?id=" + bookId;
    }
    @PostMapping("/removeBook")
    public String removeBookFromFavorite(@RequestParam int bookId, Authentication authentication) {
        userBookService.removeBookFromUserBooks(bookId, authentication);

        return "redirect:/book?id=" + bookId;
    }

    @PostMapping("/postReview")
    public String postReview(@ModelAttribute("blank_review") Review review, @RequestParam int bookId, Authentication authentication) {
        review.setUser(userRepository.findByUsername(authentication.getName()));
        review.setPostDate(Date.valueOf(LocalDate.now()));

        Book book = bookRepository.findBookById(bookId);
        book.getReviews().add(review);

        log.info("User: " + review.getUser().getUsername());
        log.info("Text: " + review.getText());
        log.info("Date: " + review.getPostDate().toString());

        reviewRepository.save(review);
        bookRepository.save(book);

        return "redirect:/book?id=" + bookId;
    }

    @Secured({ "ROLE_ADMIN", "ROLE_MODERATOR" })
    @GetMapping("/deleteReview")
    public String deleteReview(@RequestParam int bookId, @RequestParam long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null)
            reviewRepository.delete(review);

        return "redirect:/book?id=" + bookId;
    }
}
