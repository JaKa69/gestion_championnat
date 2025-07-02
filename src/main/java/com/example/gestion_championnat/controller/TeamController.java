package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TeamController {
    private final TeamService teamService;
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams/{id}")
    public String viewTeam(@PathVariable Long id, Model model) throws Exception {
        Team team = teamService.getById(id);
        model.addAttribute("team", team);
        return "public/team";
    }
    @GetMapping("/teams/")
    public String viewTeamList(@PathVariable Long id, Model model) throws Exception {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "public/team";
    }
    @GetMapping("/admin/teams")
    public String adminTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        model.addAttribute("teams", teams);
        return "private/teams";
    }

}
