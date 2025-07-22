package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Game;
import com.example.gestion_championnat.service.DayService;
import com.example.gestion_championnat.service.GameService;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showMatchesByDay(Model model) {
        model.addAttribute("days", dayService.getAllDays());
        return "public/games";
    }
    @GetMapping("/admin/matches")
    public String adminMatches(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "private/games";
    }
    @PostMapping("/admin/games/delete")
    public String deleteMatch(@RequestParam Long id) {
        gameService.deleteById(id);
        return "redirect:/admin/matches";
    }
    @GetMapping("/admin/games/new")
    public String createGameForm(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("days", dayService.getAllDays());
        return "private/game-form";
    }
    @GetMapping("/admin/games/edit/{id}")
    public String editGameForm(@PathVariable Long id, Model model) throws Exception {
        Game game = gameService.getById(id);
        model.addAttribute("game", game);
        model.addAttribute("teams", teamService.getAllTeams());
        model.addAttribute("days", dayService.getAllDays());
        return "private/game-form";
    }
    @PostMapping("/admin/games/save")
    public String saveGame(@ModelAttribute Game game) {
        gameService.save(game);
        return "redirect:/admin/matches";
    }
    @PostMapping("/admin/games/update")
    public String updateGame(@ModelAttribute Game game) {
        gameService.save(game);
        return "redirect:/admin/matches";
    }
}
