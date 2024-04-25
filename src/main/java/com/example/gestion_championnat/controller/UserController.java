package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User userToSave) {
        return ResponseEntity.status(
                HttpStatus.CREATED
        ).body(
                this.userRepository.save(userToSave)
        );
    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userRepository.findById(userId).orElse(null), HttpStatus.OK);
    }

    @PutMapping("/update/{user}")
    public ResponseEntity<User> updateUser (@PathVariable(name = "user", required = false) User user,
                                          @Valid @RequestBody User userUpdate) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable");
        } else {
            userUpdate.setId(user.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    userRepository.save(userUpdate)
            );
        }
    }

    @DeleteMapping("/{user}")
    public void deleteUserById(@PathVariable(name = "user", required = false) User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user introuvable");
        } else {
            userRepository.delete(user);
        }
    }
}
