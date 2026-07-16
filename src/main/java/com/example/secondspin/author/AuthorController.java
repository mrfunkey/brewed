package com.example.secondspin.author;

import com.example.secondspin.post.Post;
import com.example.secondspin.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PostService postService;

    @GetMapping()
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        return author != null ? ResponseEntity.ok(author) : ResponseEntity.notFound().build();
    }

    @GetMapping("/email")
    public Author getAuthorByEmail(@RequestParam String email) {
        return authorService.getAuthorByEmail(email);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getAuthorPosts(@PathVariable Long id) {
        return postService.getAuthorPosts(id);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
    }
}
