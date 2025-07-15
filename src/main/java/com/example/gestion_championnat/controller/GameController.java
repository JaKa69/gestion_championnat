package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Day;
import com.example.gestion_championnat.model.Game;
import com.example.gestion_championnat.service.DayService;
import com.example.gestion_championnat.service.GameService;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {
    private final GameService gameService;
    private final DayService dayService;
    private final TeamService teamService;

    public GameController(GameService gameService, DayService dayService, TeamService teamService) {
        this.gameService = gameService;
        this.dayService = dayService;
        this.teamService = teamService;
    }
    @GetMapping("/matches")
    public String showMatchesByDay(
            @RequestParam(value = "dayId", required = false) Long dayId,
            Model model) throws Exception {

        List<Day> allDays = dayService.getAllDays();
        Day selectedDay;

        if (dayId == null && !allDays.isEmpty()) {
            selectedDay = allDays.getFirst();
        } else {
            selectedDay = dayService.getById(dayId);
        }

        int currentIndex = allDays.indexOf(selectedDay);

        Day previousDay = currentIndex > 0 ? allDays.get(currentIndex - 1) : null;
        Day nextDay = currentIndex < allDays.size() - 1 ? allDays.get(currentIndex + 1) : null;

        model.addAttribute("days", allDays);
        model.addAttribute("selectedDay", selectedDay);
        model.addAttribute("previousDay", previousDay);
        model.addAttribute("nextDay", nextDay);

        return "public/games";
    }
    // Affichage de tous les matchs (admin)
    @GetMapping("/admin/matches")
    public String adminMatches(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "private/games";
    }

    // Formulaire de création d’un match
    @GetMapping("/admin/matches/new")
    public String showCreateForm(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("days", dayService.getAllDays());
        model.addAttribute("teams", gameService.getAllGames());
        return "private/game-form";
    }

    // Formulaire d’édition
    @GetMapping("/admin/matches/edit")
    public String showEditForm(@RequestParam Long id, Model model) throws Exception {
        model.addAttribute("game", gameService.getById(id));
        model.addAttribute("days", dayService.getAllDays());
        model.addAttribute("teams", teamService.getAllTeams());
        return "private/game-form";
    }

    // Sauvegarde d’un match
    @PostMapping("/admin/matches/save")
    public String saveMatch(@ModelAttribute Game game) {
        gameService.save(game);
        return "redirect:/admin/games";
    }

    // Suppression d’un match
    @PostMapping("/admin/matches/delete")
    public String deleteMatch(@RequestParam Long id) {
        gameService.deleteById(id);
        return "redirect:/admin/games";
    }

}
