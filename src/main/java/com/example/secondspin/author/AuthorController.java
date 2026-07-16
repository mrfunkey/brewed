package com.example.secondspin.author;

import com.example.secondspin.post.Post;
import com.example.secondspin.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @GetMapping("/email")
    public Author getAuthorByEmail(@RequestParam String email) {
        return authorService.getAuthorByEmail(email);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getAuthorPosts(@PathVariable Long id) {
        return postService.getAuthorPosts(id);
    }



    @PostMapping()
    public void createAuthor(@RequestBody Author author) {
        authorService.createAuthor(author);
    }
}
