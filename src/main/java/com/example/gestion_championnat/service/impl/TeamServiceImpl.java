package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.repository.TeamRepository;
import com.example.gestion_championnat.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team getById(Long id) throws Exception {
        return teamRepository.findById(id).orElseThrow(() -> new Exception("Team with id: " + id + " not found"));
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public void save(Team team) {
        teamRepository.save(team);
    }

    @Override
    public void delete(Long teamId) {
        teamRepository.deleteById(teamId);
    }
}
