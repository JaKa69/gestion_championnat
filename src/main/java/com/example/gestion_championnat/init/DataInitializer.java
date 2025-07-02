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

        // --- Users ---
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setFirstName("Admin");
            admin.setLastName("User");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setCreationDate(LocalDate.now());
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);

            User user = new User();
            user.setFirstName("Jean");
            user.setLastName("Dupont");
            user.setEmail("user@example.com");
            user.setPassword(passwordEncoder.encode("user123"));
            user.setCreationDate(LocalDate.now());
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }

        // --- Country ---
        Country france = new Country();
        france.setName("France");
        france.setLogo("france.png");
        countryRepository.save(france);

        // --- Stadium ---
        Stadium parcDesPrinces = new Stadium();
        parcDesPrinces.setName("Parc des Princes");
        parcDesPrinces.setAddress("Paris");
        parcDesPrinces.setCapacity(50000);
        parcDesPrinces.setPhone("0123456789");
        stadiumRepository.save(parcDesPrinces);

        // --- Championship ---
        Championship ligue1 = new Championship();
        ligue1.setName("Ligue 1");
        ligue1.setLogo("ligue1.png");
        ligue1.setStartDate(LocalDate.of(2024, 8, 1));
        ligue1.setEndDate(LocalDate.of(2025, 5, 30));
        ligue1.setWonPoints(3);
        ligue1.setDrawPoints(1);
        ligue1.setLostPoints(0);
        ligue1.setTypeRanking("DIFFERENCE_BUTS");
        championshipRepository.save(ligue1);

        // --- Teams ---
        List<Team> teams = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Team team = new Team();
            team.setName("Équipe " + i);
            team.setCoach("Coach " + i);
            team.setPresident("Président " + i);
            team.setCreationDate(LocalDate.of(2000 + i, 1, 1));
            team.setLogo("logo" + i + ".png");
            team.setStatus("Pro");
            team.setSiege("Ville " + i);
            team.setPhone("010101010" + i);
            team.setWebSite("http://equipe" + i + ".com");
            team.setCountry(france);
            team.setStadium(parcDesPrinces);
            teamRepository.save(team);
            teams.add(team);
        }

        ligue1.setTeams(teams);
        championshipRepository.save(ligue1);

        // --- Day & Games ---
        Day journee1 = new Day();
        journee1.setNumber("1");
        journee1.setChampionship(ligue1);
        dayRepository.save(journee1);

        Game match1 = new Game();
        match1.setDay(journee1);
        match1.setTeam1(teams.get(0));
        match1.setTeam2(teams.get(1));
        match1.setTeam1Point(2);
        match1.setTeam2Point(1);
        gameRepository.save(match1);

        Game match2 = new Game();
        match2.setDay(journee1);
        match2.setTeam1(teams.get(2));
        match2.setTeam2(teams.get(3));
        match2.setTeam1Point(0);
        match2.setTeam2Point(0);
        gameRepository.save(match2);
    }
}

