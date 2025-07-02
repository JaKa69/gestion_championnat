package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Day;
import com.example.gestion_championnat.service.DayService;
import com.example.gestion_championnat.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GameController {

    private final GameService gameService;
    private final DayService dayService;

    public GameController(GameService gameService, DayService dayService) {
        this.gameService = gameService;
        this.dayService = dayService;
    }
//    @GetMapping("/game/{id}")
//    public String viewTeam(@PathVariable Long id, Model model) throws Exception {
//        Game game = gameService.getById(id);
//        model.addAttribute("game", game);
//        return "public/game";
//    }
    @GetMapping("/matches")
    public String showMatchesByDay(
            @RequestParam(value = "dayId", required = false) Long dayId,
            Model model) throws Exception {

        List<Day> allDays = dayService.getAllDays(); // triées par numéro
        Day selectedDay;

        if (dayId == null && !allDays.isEmpty()) {
            selectedDay = allDays.get(0);
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
}
