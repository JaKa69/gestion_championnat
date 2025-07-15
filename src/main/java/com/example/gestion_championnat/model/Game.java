package com.example.gestion_championnat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "l'id de l'équipe 1 est obligatoire")
    @JoinColumn(name = "idTeam1", nullable = false)
    private Team team1;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "l'id de l'équipe 2 est obligatoire")
    @JoinColumn(nullable = false, name = "idTeam2")
    private Team team2;

    @ManyToOne
    @NotNull(message = "l'id de la journée est obligatoire")
    private Day day;

    public Game(int team1Point, int team2Point, Team team1, Team team2, Day day) {
        this.team1Point = team1Point;
        this.team2Point = team2Point;
        this.team1 = team1;
        this.team2 = team2;
        this.day = day;
    }
}
