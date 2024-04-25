package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    Optional<User> findUserByEmailAndPassword(@NotNull(message = "email field cannot be null") @NotBlank(message = "email field cannot be empty") String email, @NotNull(message = "password field cannot be null") @NotBlank(message = "password field cannot be empty") String password);

    Optional<User> findUserByEmail(String email);
}
