package com.example.secondspin.author;

import com.example.secondspin.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(long id) {
        return authorRepository.findById(id).get();
    }

    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email).get();
    }

    public void createAuthor(Author author) {
        authorRepository.save(author);
    }
}
