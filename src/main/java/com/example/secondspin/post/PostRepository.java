package com.example.secondspin.post;

import com.example.secondspin.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedAtAfter(LocalDateTime cutoff);
    List<Post> findByAuthorId(Long authorId);
    List<Post> findAllByOrderByCreatedAtDesc();
}
