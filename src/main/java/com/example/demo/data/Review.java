package com.example.demo.data;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "text_content")
    private String text;
    @Basic
    @Column(name = "post_date")
    private Date postDate;

    @JoinTable(
            name = "review_user",
            joinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @ManyToOne
    private User user;

    @JoinTable(
            name = "replies",
            joinColumns = @JoinColumn(name = "original_review", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "reply", referencedColumnName = "id")
    )
    @OneToMany
    private List<Review> replies;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", postDate=" + postDate +
                ", user=" + user.getUsername() +
                '}';
    }
}
