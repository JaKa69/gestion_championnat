package com.example.gestion_championnat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    //EAGER : les données de l'équipe 1 et de l'équipe 2 sont chargées en même temps que les données du match
    //car cela pose problème si on charge les données de l'équipe 1 et de l'équipe 2 en mode LAZY
    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "l'id de l'équipe 1 est obligatoire")
    private Team team1;

    @NotNull(message = "l'id de l'équipe 2 est obligatoire")
    @ManyToOne(fetch = FetchType.EAGER)
    private Team team2;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull(message = "l'id de la journée est obligatoire")
    private Day day;
}
