package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.model.User;
import com.example.gestion_championnat.service.ChampionshipService;
import com.example.gestion_championnat.service.TeamService;
import com.example.gestion_championnat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ThymeleafController {
    private final TeamService teamService;
    private final ChampionshipService championshipService;
    private final UserService userService;

    public ThymeleafController(TeamService teamService, ChampionshipService championshipService, UserService userService) {
        this.teamService = teamService;
        this.championshipService = championshipService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.findUserByEmail(principal.getName())
                .orElse(null);
            model.addAttribute("user", user);
        }
        return "public/home";
    }


    @GetMapping("/championships")
    public String listChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "public/championships";
    }

    @GetMapping("/championships/{id}/ranking")
    public String viewRanking(@PathVariable Long id, Model model) throws Exception {
        Championship championship = championshipService.getById(id);
        model.addAttribute("championship", championship);
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
        return "private/dashboard";
    }
    @GetMapping("/admin/home")
    public String adminHome() {
        return "private/admin-home";
    }

    @GetMapping("/admin/championships")
    public String adminChampionships(Model model) {
        List<Championship> championships = championshipService.getAllChampionships();
        model.addAttribute("championships", championships);
        return "private/championships";
    }

    @GetMapping("/admin/teams")
    public String adminTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "private/teams";
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
}
