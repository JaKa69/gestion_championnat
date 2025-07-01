package com.example.gestion_championnat.service;

import com.example.gestion_championnat.model.Team;

import java.util.List;

public interface TeamService {
    Team getById(Long id) throws Exception;
    List<Team> getAllTeams();
}
