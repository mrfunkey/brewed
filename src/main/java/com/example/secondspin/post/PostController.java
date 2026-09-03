package com.example.secondspin.post;

import com.example.secondspin.author.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<Post> getPosts(@RequestParam(required = false) String sort,
                               @RequestParam(required = false) String days) {
        if ("likes".equals(sort) && days != null) {
            return postService.getTopLikedByWeek(Integer.parseInt(days));
        }
        if ("likes".equals(sort)) {
            return postService.getTopLikedPosts();
        }
        if (days != null){
            return postService.getRecentPosts(Integer.parseInt(days));
        }
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable long id) {
        Post post = postService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return createdPost != null ? ResponseEntity.status(HttpStatus.CREATED).body(createdPost) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||  authentication instanceof AnonymousAuthenticationToken) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Post post = postService.getPostById(id);
        if (post == null) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else if (!Objects.equals(post.getAuthor().getEmail(), authentication.getName())){
            return   ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

}
