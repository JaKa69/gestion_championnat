package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.repository.ChampionshipRepository;
import com.example.gestion_championnat.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/team")
public class TeamController {

   private final TeamRepository teamRepository;
   private final ChampionshipRepository championshipRepository;

    public TeamController(TeamRepository teamRepository, ChampionshipRepository championshipRepository) {
        this.teamRepository = teamRepository;
        this.championshipRepository = championshipRepository;
    }

    // Récupérer la liste des équipes
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeam() {
        return new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

    // Récupérer la liste des équipes suivant l’id d’un championnat
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<Optional<List<Team>>> getTeamByChampionshipId(@PathVariable Long championshipId) {
        Optional<List<Team>> teams = teamRepository.findByChampionshipList_Id(championshipId);
        if (teams.isPresent()) {
            return ResponseEntity.ok(teams);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer une équipe suivant son id
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long teamId) {
        return new ResponseEntity<>(teamRepository.findById(teamId).orElse(null), HttpStatus.OK);
    }

    // Créer une équipe
    @PostMapping("/create")
    public ResponseEntity<Team> saveTeam(@RequestBody Team teamToSave) {
        return new ResponseEntity<>(teamRepository.save(teamToSave), HttpStatus.CREATED);
    }

    // Ajouter une équipe à un championnat
    @PostMapping("/add/{teamId}/{championshipId}")
    public ResponseEntity<Team> addTeamToChampionship(@PathVariable Long teamId, @PathVariable Long championshipId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        Optional<Championship> championshipOptional = championshipRepository.findById(championshipId);

        if (teamOptional.isPresent() && championshipOptional.isPresent()) {
            Team team = teamOptional.get();
            Championship championship = championshipOptional.get();

            team.getChampionshipList().add(championship);
            championship.getTeamList().add(team);

            teamRepository.save(team);
            championshipRepository.save(championship);

            return ResponseEntity.ok(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Mettre à jour une équipe
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable Team teamId, @RequestBody Team teamToUpdate) {
        if (teamId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            teamToUpdate.setId(teamId.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    teamRepository.save(teamToUpdate)
            );
        }
    }


    // Supprimer une équipe
    @DeleteMapping("/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);

        if (team.isPresent()) {
            teamRepository.deleteById(teamId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>("La Team n'existe pas", HttpStatus.NOT_FOUND);
        }
    }
}
