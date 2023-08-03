package com.example.demo.crud;

import com.example.demo.data.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.data.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, String> {
    List<Book> findBookByTitleContainingIgnoreCase(String title);

    Optional<Book> findBookById(int id);
}
