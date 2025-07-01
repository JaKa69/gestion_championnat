package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.repository.TeamRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeam() {
        return new ResponseEntity<>(teamRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<List<Team>> getTeamsByChampionshipId(@PathVariable Long championshipId) {
        return ResponseEntity.of(
                teamRepository.findByChampionshipId(championshipId)
        );
    }
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamsById(@PathVariable Long teamId) {
        return ResponseEntity.of(
                teamRepository.findById(teamId)
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Team> saveTeam(@RequestBody Team teamToSave) {
        teamToSave.setCreationDate(LocalDate.now());
        return new ResponseEntity<>(teamRepository.save(teamToSave), HttpStatus.CREATED);
    }

    @PutMapping("/update/{team}")
    public ResponseEntity<Team> updateTeam (@PathVariable(name = "team", required = false) Team team,
                                                            @Valid @RequestBody Team teamUpdate) {
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            teamUpdate.setId(team.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    teamRepository.save(teamUpdate)
            );
        }
    }

    @PutMapping("/add/{team}/{championship}")
    public ResponseEntity<Team> addTeamInChampionship (@PathVariable(name = "team", required = false) Team team,
                                            @PathVariable(name = "championship") Championship championshipToAdd) {
        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            team.getChampionships().add(championshipToAdd);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    teamRepository.save(team)
            );
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable Long id) {
        teamRepository.deleteById(id);
    }
}
