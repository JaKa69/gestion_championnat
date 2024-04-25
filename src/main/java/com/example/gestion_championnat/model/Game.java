package com.example.gestion_championnat.model;

import jakarta.persistence.*;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "l'id de l'équipe 1 est obligatoire")
    private Team team1;
    @NotNull(message = "l'id de l'équipe 2 est obligatoire")
    @ManyToOne(fetch = FetchType.LAZY)
    private Team team2;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "l'id de la journée est obligatoire")
    private Day idDay;

}
