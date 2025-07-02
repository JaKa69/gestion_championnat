package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.repository.ChampionshipRepository;
import com.example.gestion_championnat.repository.DayRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/day")
public class DayController {

    private final DayRepository dayRepository;

    private final ChampionshipRepository championshipRepository;

    public DayController(DayRepository dayRepository, ChampionshipRepository championshipRepository) {
        this.dayRepository = dayRepository;
        this.championshipRepository = championshipRepository;
    }

}
