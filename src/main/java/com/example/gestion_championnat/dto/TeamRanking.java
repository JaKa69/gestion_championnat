package com.example.gestion_championnat.dto;

import com.example.gestion_championnat.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamRanking {
    private Team team;
    private int points;
    private int played;
    private int won;
    private int draw;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalDifference;
    private List<String> form;
}

