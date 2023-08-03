package com.example.demo.data;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    @Column(name = "publishing_year")
    private String publishingYear;
    @ManyToOne
//    @Column(name = "author")
    @JoinColumn(name = "author")
    private Author author;
}
