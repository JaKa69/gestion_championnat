package com.example.gestion_championnat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "firstName field cannot be null")
    @NotBlank(message = "firstName field cannot be empty")
    private String firstName;
    @NotNull(message = "lastName field cannot be null")
    @NotBlank(message = "lastName field cannot be empty")
    private String lastName;
    @NotNull(message = "email field cannot be null")
    @NotBlank(message = "email field cannot be empty")
    private String email;
    @NotNull(message = "password field cannot be null")
    @NotBlank(message = "password field cannot be empty")
    private String password;
    @Temporal(value= TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
}
