package com.example.secondspin.dashboard;

import com.example.secondspin.post.Post;
import com.example.secondspin.post.PostService;
import com.example.secondspin.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/recent-posts")
    public List<Post> getRecentPosts(int days) {
        return postService.getRecentPosts(days);
    }
}
