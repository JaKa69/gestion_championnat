package com.example.gestion_championnat.service;

import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        user.setPassword(PasswordEncoderService.hashPassword(user.getPassword())); // Hash the password
        user.setCreationDate(LocalDate.now());
        userRepository.save(user);
    }
    public boolean loginUser(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent() && PasswordEncoderService.verifyPassword(password, user.get().getPassword());
    }

    public boolean deleteUser(User user) {
        Long userId = user.getId();
        userRepository.delete(user);
        return userRepository.findById(userId).isEmpty();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public User UpdateUser(User userToUpdate, User userUpdate) {
        userUpdate.setId(userToUpdate.getId());
        userUpdate.setCreationDate(userToUpdate.getCreationDate());
        return userRepository.save(userUpdate);
    }
}
