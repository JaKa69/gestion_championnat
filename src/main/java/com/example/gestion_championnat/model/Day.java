package com.example.gestion_championnat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "le numéro de la journée est obligatoire")
    @NotBlank(message = "le numéro de la journée ne peut pas être vide")
    private String number;
    @ManyToOne
    @NotNull(message = "l'id du championnat est obligatoire")
    @JoinColumn( nullable = false)
    private Championship championship;
    @OneToMany(mappedBy = "day")
    private List<Game> games;
}
