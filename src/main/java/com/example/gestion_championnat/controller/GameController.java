package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Game;
import com.example.gestion_championnat.repository.GameRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/game")
public class GameController {

    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    //Récupérer la liste des résultats
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGame() {
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

    //Récupérer la liste des résultats suivant l’id d’un championnat
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<Optional<List<Game>>> getGameByChampionshipId(@PathVariable Long championshipId) {
        Optional<List<Game>> games = gameRepository.findByDayChampionshipId(championshipId);
        if (games.isPresent()) {
            return ResponseEntity.ok(games);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Récupérer la liste des résultats suivant l’id d’une journée
    @GetMapping("/day/{dayId}")
    public ResponseEntity<List<Game>> getGameByDayId(@PathVariable Long dayId) {
        Optional<List<Game>> gamesOptional = gameRepository.findByDayId(dayId);
        if (gamesOptional.isPresent()) {
            List<Game> games = gamesOptional.get();
            return ResponseEntity.ok(games);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Récupérer un résultat suivant son id
    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable Long gameId) {
        return new ResponseEntity<>(gameRepository.findById(gameId).orElse(null), HttpStatus.OK);
    }

    // Créer un résultat pour une journée.
    @PostMapping("/create")
    public ResponseEntity<Game> saveGame(@RequestBody Game gameToSave) {
        return new ResponseEntity<>(gameRepository.save(gameToSave), HttpStatus.CREATED);
    }

    // Mettre à jour un résultat
    @PutMapping("/{gameId}")
    public ResponseEntity<Game> updateGame(@PathVariable Game gameId, @RequestBody Game gameToUpdate) {
        if (gameId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            gameToUpdate.setId(gameId.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    gameRepository.save(gameToUpdate)
            );
        }
    }

    // Supprimer un résultat
    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGame(@PathVariable Long gameId) {
        gameRepository.deleteById(gameId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
