package com.example.secondspin.author;

import com.example.secondspin.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email).orElse(null);
    }

    public Author createAuthor(Author author) {
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        return authorRepository.save(author);
    }

    public Author login(String email, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authenticationManager.authenticate(token));

        Author author = authorRepository.findByEmail(token.getName()).get();
        return author;
    }

}
