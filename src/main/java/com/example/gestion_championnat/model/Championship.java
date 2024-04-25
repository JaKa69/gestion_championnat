package com.example.gestion_championnat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Championship {
    @Id
    private Long id;
    @NotNull(message = "le nom du championnat est obligatoire")
    @NotBlank(message = "le nom du championnat ne peut pas être vide")
    private String name;
    @NotNull(message = "la date de début du championnat est obligatoire")
    private LocalDate startDate;
    @NotNull(message = "la date de fin du championnat est obligatoire")
    private LocalDate endDate;
    @NotNull(message = "le nombre de points pour une victoire est obligatoire")
    private int wonPoints;
    @NotNull(message = "le nombre de points pour une défaite est obligatoire")
    private int lostPoints;
    @NotNull(message = "le nombre de points pour un match nul est obligatoire")
    private int drawPoints;




}
