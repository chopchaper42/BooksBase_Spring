package com.example.demo.crud;

import com.example.demo.data.Author;
import org.springframework.data.repository.CrudRepository;


public interface AuthorRepository extends CrudRepository<Author, Long> {
    Author getAuthorById(int id);
}
