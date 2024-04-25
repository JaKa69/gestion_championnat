package com.example.gestion_championnat.controller;
import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.repository.ChampionshipRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/championship")
public class ChampionshipController {

    private final ChampionshipRepository championshipRepository;

    public ChampionshipController(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    //Récupérer tous les championnats
    @GetMapping("/all")
    public ResponseEntity<List<Championship>> getAllChampionship() {
        return new ResponseEntity<>(championshipRepository.findAll(), HttpStatus.OK);

    }

    //Récupérer par ID
    @GetMapping("/{championshipId}")
    public ResponseEntity<Championship> getChampionshipById(@PathVariable Long championshipId) {
        return new ResponseEntity<>(championshipRepository.findById(championshipId).orElse(null), HttpStatus.OK);
    }

    //Créer un championnat
    @PostMapping("/create")
    public ResponseEntity<Championship> saveChampionship(@Valid @RequestBody Championship championshipToSave) {
        return new ResponseEntity<>(championshipRepository.save(championshipToSave), HttpStatus.CREATED);
    }
    
    //Mettre à jour un championnat
    @PutMapping("/update/{championship}")
    public ResponseEntity<Championship> updateChampionship (@PathVariable(name = "championship", required = false) Championship championship,
                                                                  @Valid @RequestBody Championship championshipUpdate) {
        if (championship == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            championshipUpdate.setId(championship.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    championshipRepository.save(championshipUpdate)
            );
        }
    }
    //Supprimer un championnat
    @DeleteMapping("/delete/{championshipId}")
    public ResponseEntity<?> deleteChampionship(@PathVariable Long championshipId) {
        championshipRepository.deleteById(championshipId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

