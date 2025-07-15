package com.example.gestion_championnat.init;

import com.example.gestion_championnat.model.*;
import com.example.gestion_championnat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired private ChampionshipRepository championshipRepository;
    @Autowired private TeamRepository teamRepository;
    @Autowired private StadiumRepository stadiumRepository;
    @Autowired private CountryRepository countryRepository;
    @Autowired private DayRepository dayRepository;
    @Autowired private GameRepository gameRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
//        // --- Users ---
//        if (userRepository.count() == 0) {
//            User admin = new User("Admin", "User", "admin@example.com", passwordEncoder.encode("admin123"), LocalDate.now(), "ROLE_ADMIN");
//            User user = new User("Jean", "Dupont", "user@example.com", passwordEncoder.encode("user123"), LocalDate.now(), "ROLE_USER");
//            userRepository.save(admin);
//            userRepository.save(user);
//        }
//
//        // --- Country ---
//        Country france = new Country();
//        france.setName("France");
//        france.setLogo("france.png");
//        countryRepository.save(france);
//
//        // --- Stadiums ---
//        Stadium parcDesPrinces = new Stadium("Parc des Princes", "Paris", 47929, "0141234567");
//        Stadium velodrome = new Stadium("Stade Vélodrome", "Marseille", 67394, "0491888888");
//        Stadium groupama = new Stadium("Groupama Stadium", "Lyon", 59186, "0478000000");
//        Stadium stadePierreMauroy = new Stadium("Stade Pierre-Mauroy", "Lille", 50000, "0320222222");
//
//        stadiumRepository.saveAll(List.of(parcDesPrinces, velodrome, groupama, stadePierreMauroy));
//
//        // --- Ligue 1 ---
//        Championship ligue1 = new Championship();
//        ligue1.setName("Ligue 1 Uber Eats");
//        ligue1.setLogo("ligue1.png");
//        ligue1.setStartDate(LocalDate.of(2024, 8, 9));
//        ligue1.setEndDate(LocalDate.of(2025, 5, 25));
//        ligue1.setWonPoints(3);
//        ligue1.setDrawPoints(1);
//        ligue1.setLostPoints(0);
//        ligue1.setTypeRanking("DIFFERENCE_BUTS");
//        championshipRepository.save(ligue1);
//
//        List<Team> ligue1Teams = List.of(
//                new Team("Paris Saint-Germain", LocalDate.of(1970, 8, 12), "psg.png", "Luis Enrique", "Nasser Al-Khelaïfi", "Pro", "Paris", "0145678910", "https://psg.fr", france, parcDesPrinces),
//                new Team("Olympique de Marseille", LocalDate.of(1899, 8, 31), "om.png", "Roberto De Zerbi", "Pablo Longoria", "Pro", "Marseille", "0491880000", "https://om.net", france, velodrome),
//                new Team("Olympique Lyonnais", LocalDate.of(1950, 5, 27), "ol.png", "Paulo Fonseca", "Michele Kang", "Pro", "Lyon", "0478006060", "https://ol.fr", france, groupama),
//                new Team("LOSC Lille", LocalDate.of(1944, 9, 1), "losc.png", "Paulo Fonseca", "Olivier Létang", "Pro", "Lille", "0320222222", "https://losc.fr", france, stadePierreMauroy)
//        );
//
//        teamRepository.saveAll(ligue1Teams);
//        ligue1.setTeams(ligue1Teams);
//        championshipRepository.save(ligue1);
//
//        // --- Ligue 2 ---
//        Championship ligue2 = new Championship();
//        ligue2.setName("Ligue 2 BKT");
//        ligue2.setLogo("ligue2.png");
//        ligue2.setStartDate(LocalDate.of(2024, 8, 16));
//        ligue2.setEndDate(LocalDate.of(2025, 5, 23));
//        ligue2.setWonPoints(3);
//        ligue2.setDrawPoints(1);
//        ligue2.setLostPoints(0);
//        ligue2.setTypeRanking("DIFFERENCE_BUTS");
//        championshipRepository.save(ligue2);
//
//        Stadium stadeSochaux = new Stadium("Stade Bonal", "Montbéliard", 20005, "0381693030");
//        Stadium stadeSaintSymphorien = new Stadium("Stade Saint-Symphorien", "Metz", 25836, "0387360000");
//
//        stadiumRepository.saveAll(List.of(stadeSochaux, stadeSaintSymphorien));
//
//        List<Team> ligue2Teams = List.of(
//                new Team("FC Sochaux", LocalDate.of(1928, 6, 18), "sochaux.png", "Oswald Tanchot", "Samuel Laurent", "Pro", "Sochaux", "0381690000", "https://fcsochaux.fr", france, stadeSochaux),
//                new Team("FC Metz", LocalDate.of(1932, 4, 27), "metz.png", "László Bölöni", "Bernard Serin", "Pro", "Metz", "0387361212", "https://fcmetz.com", france, stadeSaintSymphorien)
//        );
//
//        teamRepository.saveAll(ligue2Teams);
//        ligue2.setTeams(ligue2Teams);
//        championshipRepository.save(ligue2);
//
//        // --- Days and Games for Ligue 1 ---
//        Day day1 = new Day();
//        day1.setNumber("1");
//        day1.setDate(LocalDate.of(2024, 8, 10));
//        day1.setChampionship(ligue1);
//        dayRepository.save(day1);
//
//        Game game1 = new Game(2, 1, ligue1Teams.get(0), ligue1Teams.get(1), day1);
//        Game game2 = new Game(1, 1, ligue1Teams.get(2), ligue1Teams.get(3), day1);
//        gameRepository.saveAll(List.of(game1, game2));
//        // --- Days and Games for Ligue 2 ---
//        Day day2 = new Day();
//        day2.setNumber("1");
//        day2.setDate(LocalDate.of(2024, 8, 17));
//        day2.setChampionship(ligue2);
//        dayRepository.save(day2);
//
//        // Match aller Sochaux vs Metz
//        Game game3 = new Game(1, 0, ligue2Teams.get(0), ligue2Teams.get(1), day2);
//        gameRepository.save(game3);
    }
}

