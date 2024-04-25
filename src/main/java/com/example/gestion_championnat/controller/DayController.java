package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Day;
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

    public DayController(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Day>> getAllDay() {
        return new ResponseEntity<>(dayRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{dayId}")
    public ResponseEntity<Day> getDayById(@PathVariable Long dayId) {
        return new ResponseEntity<>(dayRepository.findById(dayId).orElse(null), HttpStatus.OK);
    }

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



    @PostMapping("/create")
    public ResponseEntity<Day> saveDay(@RequestBody Day dayToSave) {
        return new ResponseEntity<>(dayRepository.save(dayToSave), HttpStatus.CREATED);
    }
}
