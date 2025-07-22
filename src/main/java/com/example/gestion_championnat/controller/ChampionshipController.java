package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.dto.TeamRanking;
import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Day;
import com.example.gestion_championnat.service.ChampionshipService;
import com.example.gestion_championnat.service.DayService;
import com.example.gestion_championnat.service.RankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class ChampionshipController {

    private final ChampionshipService championshipService;
    private final RankingService rankingService;
    private final DayService dayService;
    public ChampionshipController(ChampionshipService championshipService, RankingService rankingService, DayService dayService) {
        this.championshipService = championshipService;
        this.rankingService = rankingService;
        this.dayService = dayService;
    }
    @GetMapping("/championships")
    public String listChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "public/championships";
    }
    @GetMapping("/championship/matches")
    public String listAllMatchesByChampionship(
            @RequestParam(value = "championshipId", required = false) Long championshipId,
            Model model) {

        List<Day> allDays = dayService.getAllDays();

        // Filtrer les journées liées au championnat demandé
        List<Day> filteredDays = allDays.stream()
                .filter(day -> Objects.equals(day.getChampionship().getId(), championshipId))
                .toList();

        model.addAttribute("days", filteredDays);

        // Récupérer le nom du championnat (s'il y a au moins une journée)
        String championshipName = filteredDays.isEmpty() ? "Inconnu" :
                filteredDays.getFirst().getChampionship().getName();

        model.addAttribute("championShipName", championshipName);

        return "public/games";
    }

    @GetMapping("/championships/ranking")
    public String showRanking(@RequestParam(value = "championshipId", required = false) Long championshipId, Model model) throws Exception {
        List<Championship> allChampionships = championshipService.getAllChampionships();
        model.addAttribute("allChampionships", allChampionships);

        if (championshipId != null) {
            Championship selectedChampionship = championshipService.getById(championshipId);
            model.addAttribute("selectedChampionship", selectedChampionship);

            if (selectedChampionship != null) {
                List<TeamRanking> rankings = rankingService.calculateRanking(selectedChampionship);
                model.addAttribute("rankings", rankings);
            }
        }

        return "public/ranking";
    }
    @GetMapping("/admin/championships/new")
    public String createTeamForm(Model model) {
        model.addAttribute("championship", new Championship());
        return "private/championship-form";
    }
    @GetMapping("/admin/championships/edit/{id}")
    public String showEditChampionshipForm(@PathVariable Long id, Model model) throws Exception {
        Championship champ = championshipService.getById(id);
        model.addAttribute("championship", champ);
        return "private/championship-form";
    }

    @PostMapping("/admin/championships/save")
    public String saveChampionship(@ModelAttribute("championship") Championship championship) {
        championshipService.save(championship);
        return "redirect:/championships";
    }
    @GetMapping("/admin/championships")
    public String adminChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "private/championships";
    }
    @PostMapping("/admin/championships/delete/{championshipId}")
    public String deleteChampionship(@PathVariable Long championshipId) {
        championshipService.delete(championshipId);
        return "redirect:/admin/championships";
    }
}

