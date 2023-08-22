package com.example.demo.crud;

import com.example.demo.data.Author;
import com.example.demo.data.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.data.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findBookByTitleContainingIgnoreCase(String title);

    Book findBookById(long id);

    @Query("select b from Book b order by RANDOM() limit 3")
    List<Book> getTrendingBooks();

    List<Book> getBooksByAuthorAndIdNot(Author author, long bookId);
}
