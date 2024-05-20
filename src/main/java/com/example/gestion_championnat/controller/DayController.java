package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Day;
import com.example.gestion_championnat.repository.ChampionshipRepository;
import com.example.gestion_championnat.repository.DayRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/day")
public class DayController {

    private final DayRepository dayRepository;

    private final ChampionshipRepository championshipRepository;

    public DayController(DayRepository dayRepository, ChampionshipRepository championshipRepository) {
        this.dayRepository = dayRepository;
        this.championshipRepository = championshipRepository;
    }

    //Récupérer la liste des journées
    @GetMapping("/all")
    public ResponseEntity<List<Day>> getAllDay() {
        return new ResponseEntity<>(dayRepository.findAll(), HttpStatus.OK);
    }

    //Récupérer par ID
    @GetMapping("/{dayId}")
    public ResponseEntity<Day> getDayById(@PathVariable Long dayId) {
        return new ResponseEntity<>(dayRepository.findById(dayId).orElse(null), HttpStatus.OK);
    }

    // Récupérer la liste des journées suivant l’id d’un championnat
    @GetMapping("/championship/{championshipId}")
    public ResponseEntity<List<Day>> getDayByChampionshipId(@PathVariable Long championshipId) {
        Optional<List<Day>> daysOptional = dayRepository.findByChampionshipId(championshipId);
        if (daysOptional.isPresent()) {
            List<Day> days = daysOptional.get();
            return ResponseEntity.ok(days);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Créer une journée pour un championnat
    @PostMapping("/create")
    public ResponseEntity<Day> saveDay(@RequestBody Day dayToSave) {
        // Récupérer le championnat associé à partir de la base de données

        Championship championship = championshipRepository.findById(dayToSave.getChampionship().getId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid championship Id"));

        // Associer le championnat à la journée
        dayToSave.setChampionship(championship);

        // Enregistrer la journée avec son championnat associé
        Day savedDay = dayRepository.save(dayToSave);
        return new ResponseEntity<>(savedDay, HttpStatus.CREATED);
    }

    // Mettre à jour une journée
    @PutMapping("/{dayId}")
    public ResponseEntity<Day> updateDay(@PathVariable Day dayId, @RequestBody Day dayToUpdate) {
        if (dayId == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            dayToUpdate.setId(dayId.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    dayRepository.save(dayToUpdate)
            );
        }
    }



    //Supprimer une journée

    @DeleteMapping("/{dayId}")
    public ResponseEntity<?> deleteDay(@PathVariable Long dayId) {
        dayRepository.deleteById(dayId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
