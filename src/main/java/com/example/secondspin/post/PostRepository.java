package com.example.secondspin.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCreatedAtAfter(LocalDateTime cutoff);
    List<Post> findByAuthorId(Long authorId);
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findTop10ByOrderByLikesDesc();
}
