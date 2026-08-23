package com.example.secondspin.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthorDetailsService implements UserDetailsService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Author author = authorRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        return User.withUsername(author.getEmail())
                .password(author.getPassword())
                .authorities(Collections.emptyList())
                .build();
    }
}
