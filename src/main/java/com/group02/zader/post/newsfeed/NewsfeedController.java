package com.group02.zader.post.newsfeed;

import com.group02.zader.auth.CustomUserDetails;
import com.group02.zader.common.dto.PostCreateDTO;
import com.group02.zader.post.PostService;
import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class NewsfeedController {

    private final PostService postService;

    public NewsfeedController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String showNewsfeed(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // user info
        if (userDetails != null) {
            model.addAttribute("user", userDetails.getMember());
        } else {
            model.addAttribute("user", null);
        }
        
        // post info
        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("postCreateDto", new PostCreateDTO());
        return "guest/newsfeed";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostCreateDTO postCreateDto, Principal principal) {
        try {
            postService.createPost(postCreateDto, principal.getName());
        } catch (IOException e) {
            return "redirect:/?errorMessage=Error creating post";
        }
        return "redirect:/?successMessage=Post created successfully";
    }
}