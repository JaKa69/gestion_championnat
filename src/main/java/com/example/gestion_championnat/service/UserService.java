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

    public boolean deleteUser(User user) {
        Long userId = user.getId();
        userRepository.delete(user);
        return userRepository.findById(userId).isEmpty();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("user not found with id: " + userId));
    }
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User UpdateUser(User userToUpdate, User userUpdate) {
        userUpdate.setFirstName(userToUpdate.getFirstName());
        userUpdate.setLastName(userToUpdate.getLastName());
        userUpdate.setEmail(userToUpdate.getEmail());
        userUpdate.setRole(userToUpdate.getRole());
        return userRepository.save(userUpdate);
    }
}
