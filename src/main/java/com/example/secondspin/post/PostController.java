package com.example.secondspin.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<Post> getPosts(@RequestParam(required = false) String sort,
                               @RequestParam(required = false) String days) {
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

}
