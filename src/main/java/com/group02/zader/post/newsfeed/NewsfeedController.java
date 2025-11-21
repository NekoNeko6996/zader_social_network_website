package com.group02.zader.post.newsfeed;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsfeedController {

    @GetMapping("/")
    public String home(Model model) {
        // Gửi dữ liệu mẫu sang HTML
        model.addAttribute("message", "Chào mừng đến với Zader Social Network!");
        return "index"; // Trả về file index.html
    }
}