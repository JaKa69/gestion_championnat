package com.example.gestion_championnat.controller;

import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.service.CountryService;
import com.example.gestion_championnat.service.StadiumService;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TeamController {
    private final TeamService teamService;
    private final CountryService countryService;
    private final StadiumService stadiumService;
    public TeamController(TeamService teamService, CountryService countryService, StadiumService stadiumService) {
        this.teamService = teamService;
        this.countryService = countryService;
        this.stadiumService = stadiumService;
    }

    @GetMapping("/teams/{id}")
    public String viewTeam(@PathVariable Long id, Model model) throws Exception {
        Team team = teamService.getById(id);
        model.addAttribute("team", team);
        return "public/team";
    }
    @GetMapping("/teams/")
    public String viewTeamList(Model model) {
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
    @GetMapping("/admin/teams/new")
    public String createTeamForm(Model model) {
        model.addAttribute("team", new Team());
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("stadiums", stadiumService.getAllStadiums());
        return "private/team-form";
    }

    @GetMapping("/admin/teams/edit/{id}")
    public String editTeamForm(@PathVariable Long id, Model model) throws Exception {
        Team team = teamService.getById(id);
        model.addAttribute("team", team);
        model.addAttribute("countries", countryService.getAllCountries());
        model.addAttribute("stadiums", stadiumService.getAllStadiums());
        return "private/team-form";
    }

    @PostMapping("/admin/teams/save")
    public String saveTeam(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/admin/teams";
    }

    @PostMapping("/admin/teams/update")
    public String updateTeam(@ModelAttribute Team team) {
        teamService.save(team);
        return "redirect:/admin/teams";
    }
    @PostMapping("/admin/teams/delete/{teamId}")
    public String deleteTeam(@PathVariable Long teamId) {
        teamService.delete(teamId);
        return "redirect:/admin/teams";
    }


}
