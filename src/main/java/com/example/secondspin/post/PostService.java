package com.example.secondspin.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getRecentPosts(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return postRepository.findByCreatedAtAfter(cutoff);
    }

    public List<Post> getAuthorPosts(Long id) {
        return postRepository.findByAuthorId(id);
    }
}
