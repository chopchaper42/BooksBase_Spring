package com.example.demo.utilities;

import com.example.demo.crud.BookRepository;
import com.example.demo.crud.UserRepository;
import com.example.demo.data.Book;
import com.example.demo.data.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@Slf4j
public class UserBookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserBookService(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void initModelWithUserBooks(Model model, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        model.addAttribute("userBooks", user.getBooks());
        log.info("User books: " + user.getBooks());
    }

    public void addReview(int bookId, Authentication authentication) {

    }

    public void addBookToUserBooks(long bookId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        user.getBooks().add(bookRepository.findBookById(bookId));
        userRepository.save(user);
    }

    public void removeBookFromUserBooks(long bookId, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName());
        Book bookToRemove = bookRepository.findBookById(bookId);
        user.getBooks().remove(bookToRemove);
        userRepository.save(user);
    }
}
