package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.dto.TeamRanking;
import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.service.ChampionshipService;
import com.example.gestion_championnat.service.RankingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChampionshipController {

    private final ChampionshipService championshipService;
    private final RankingService rankingService;
    public ChampionshipController(ChampionshipService championshipService, RankingService rankingService) {
        this.championshipService = championshipService;
        this.rankingService = rankingService;
    }
    @GetMapping("/championships")
    public String listChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "public/championships";
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
}

