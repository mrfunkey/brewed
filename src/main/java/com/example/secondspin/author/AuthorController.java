package com.example.secondspin.author;

import com.example.secondspin.post.Post;
import com.example.secondspin.post.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @Autowired
    private PostService postService;
    @Autowired
    private SecurityContextRepository securityContextRepository;

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

    @PostMapping("/signup")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(author));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAuthor(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||  authentication instanceof AnonymousAuthenticationToken) {
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Author author = authorService.getAuthorByEmail(authentication.getName());
        authorService.deleteAuthor(author);
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Author> login(@RequestBody Author author, HttpServletRequest request, HttpServletResponse response) {
        try{
            Author loggedInAuthor = authorService.login(author.getEmail(), author.getPassword());
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
            return ResponseEntity.ok(loggedInAuthor);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Author> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null ||  !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Author author = authorService.getAuthorByEmail(auth.getName());
        return ResponseEntity.ok(author);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }


}
