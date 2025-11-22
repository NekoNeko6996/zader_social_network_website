package com.group02.zader.auth;

import com.group02.zader.common.dto.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    // Inject AuthService vào Controller
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "guest/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerDto", new RegisterDTO());
        return "guest/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerDto") RegisterDTO registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            return "redirect:/register?error=password_mismatch";
        }

        try {
            authService.register(registerDto);
        } catch (Exception e) {
            /**
             * If there is an error (e.g. duplicate username), return to the registration page with an error message
             * here we temporarily use the general error param.
             */
            return "redirect:/register?error=exists";
        }
        
        // success
        return "redirect:/login?success";
    }
}