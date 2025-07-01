package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        return ResponseEntity.of(userService.findUserById(Long.valueOf(userId)));
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

    @PutMapping("/update/{user}")
    public ResponseEntity<User> updateUser (@PathVariable(name = "user", required = false) User user,
                                            @RequestBody User userUpdate) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable");
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userService.UpdateUser(user, userUpdate)
            );
        }
    }

    @DeleteMapping("/{user}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable(name = "user", required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable");
        } else {
            return ResponseEntity.ok(userService.deleteUser(user));
        }
    }
}
