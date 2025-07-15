package com.example.gestion_championnat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Championship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "le nom du championnat est obligatoire")
    @NotBlank(message = "le nom du championnat ne peut pas être vide")
    private String name;

    private String logo;

    @NotNull(message = "la date de début du championnat est obligatoire")
    @Temporal(value= TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "la date de fin du championnat est obligatoire")
    @Temporal(value= TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull(message = "le nombre de points pour une victoire est obligatoire")
    private int wonPoints;
    @NotNull(message = "le nombre de points pour une défaite est obligatoire")
    private int lostPoints;
    @NotNull(message = "le nombre de points pour un match nul est obligatoire")
    private int drawPoints;
    @Column(nullable = false)
    private String typeRanking;

    @OneToMany(mappedBy = "championship", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Day> days;
    @ManyToMany (fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @ToString.Exclude
    @JsonIgnore
    @JoinTable(name="TeamChampionShip", joinColumns = {@JoinColumn(name="idChampionship")}, inverseJoinColumns = {@JoinColumn(name="idTeam")})
    private List<Team> teams;
}
