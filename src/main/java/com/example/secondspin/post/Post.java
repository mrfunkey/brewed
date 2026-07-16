package com.example.secondspin.post;

import com.example.secondspin.author.Author;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false)
    private String content;

    @Column(name = "header_img")
    private String headerImage;

    private int likes;

    private int dislikes;

    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
}
