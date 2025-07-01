package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.service.ChampionshipService;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ThymeleafController {
    private final TeamService teamService;
    private final ChampionshipService championshipService;

    public ThymeleafController(TeamService teamService, ChampionshipService championshipService) {
        this.teamService = teamService;
        this.championshipService = championshipService;
    }

    @GetMapping("/")
    public String home() {
        return "public/home";
    }

    @GetMapping("/championships")
    public String listChampionships(Model model) {
        // Exemple : récupération de la liste des championnats depuis le service
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "public/championships";
    }

    @GetMapping("/championships/{id}/ranking")
    public String viewRanking(@PathVariable Long id, Model model) throws Exception {
        Championship championship = championshipService.getById(id);
        model.addAttribute("championship", championship);
        // récupère et ajoute le classement...
        return "public/ranking";
    }

    @GetMapping("/teams/{id}")
    public String viewTeam(@PathVariable Long id, Model model) throws Exception {
        Team team = teamService.getById(id);
        model.addAttribute("team", team);
        return "public/team";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }
    @GetMapping("/admin/home")
    public String adminHome() {
        return "admin/admin-home";
    }

    @GetMapping("/admin/championships")
    public String adminChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "admin/championships";
    }

    @GetMapping("/admin/teams")
    public String adminTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "admin/teams";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "public/register";
    }
}
