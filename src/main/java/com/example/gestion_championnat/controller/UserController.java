package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.dto.LoginRequest;
import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
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
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during registration: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            boolean loginSuccess = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (loginSuccess) {
                return ResponseEntity.ok("User logged in successfully!");
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Login error: " + e.getMessage());
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
