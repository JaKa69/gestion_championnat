package com.example.gestion_championnat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "le nom de l'équipe est obligatoire")
    @NotBlank(message = "le nom de l'équipe ne peut pas être vide")
    private String name;
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
    @ToString.Exclude
    private Country country;
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Stadium stadium;
    @ManyToMany(mappedBy = "teams")
    @ToString.Exclude
    private List<Championship> championships;
    @OneToMany(mappedBy = "team1")
    @ToString.Exclude
    private List<Game> homeGames;
    @OneToMany(mappedBy = "team2")
    @ToString.Exclude
    private List<Game> awayGames;
    public Team(String name, LocalDate creationDate, String logo, String coach, String president, String status, String siege, String phone, String webSite, Country country, Stadium stadium, List<Championship> championship) {
        this.name = name;
        this.creationDate = creationDate;
        this.logo = logo;
        this.coach = coach;
        this.president = president;
        this.status = status;
        this.siege = siege;
        this.phone = phone;
        this.webSite = webSite;
        this.country = country;
        this.stadium = stadium;
        this.championships = championship;
    }
}
