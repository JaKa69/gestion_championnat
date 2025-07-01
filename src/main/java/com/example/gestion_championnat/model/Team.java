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
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "le nom de l'équipe est obligatoire")
    @NotBlank(message = "le nom de l'équipe ne peut pas être vide")
    private String name;

    @Temporal(value= TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String coach;

    @Column(nullable = false)
    private String president;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String siege;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String webSite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Stadium stadium;

    @ManyToMany(mappedBy = "teams")
    private List<Championship> championships;

    @OneToMany(mappedBy = "team1")
    private List<Game> homeGames;

    @OneToMany(mappedBy = "team2")
    private List<Game> awayGames;
}
