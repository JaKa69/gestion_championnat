package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ThymeleafController {
    private final UserService userService;

    public ThymeleafController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findUserByEmail(principal.getName())
                .orElse(null);
            model.addAttribute("user", user);
        }
        return "public/home";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "public/register";
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", "Erreur lors de l'inscription : " + e.getMessage());
            return "public/register";
        }
    }
    //admin
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "private/dashboard";
    }
    @GetMapping("/admin/home")
    public String adminHome() {
        return "private/admin-home";
    }
}
