package com.example.gestion_championnat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "le score de l'équipe 1 est obligatoire")
    private int team1Point;
    @NotNull(message = "le score de l'équipe 2 est obligatoire")
    private int team2Point;
    @NotNull(message = "l'id de l'équipe 1 est obligatoire")
    private int team1Id;
    @NotNull(message = "l'id de l'équipe 2 est obligatoire")
    private int team2Id;
    @NotNull(message = "l'id de la journée est obligatoire")
    private int idDay;

}
