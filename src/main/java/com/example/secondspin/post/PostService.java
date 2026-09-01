package com.example.secondspin.post;

import com.example.secondspin.author.Author;
import com.example.secondspin.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<Post> getPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Post> getRecentPosts(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return postRepository.findByCreatedAtAfter(cutoff);
    }

    public List<Post> getTopLikedPosts() {
        return postRepository.findTop10ByOrderByLikesDesc();
    }

    public List<Post> getTopLikedByWeek(int days) {
        LocalDateTime cutoff = LocalDateTime.now().minusDays(days);
        return postRepository.findTop10ByCreatedAtAfterOrderByLikesDesc(cutoff);
    }

    public List<Post> getAuthorPosts(Long id) {
        return postRepository.findByAuthorId(id);
    }

    public Post getPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post createPost(Post post) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Author author = authorRepository.findByEmail(auth.getName()).orElse(null);
        post.setAuthor(author);
        return postRepository.save(post);
    }

}
