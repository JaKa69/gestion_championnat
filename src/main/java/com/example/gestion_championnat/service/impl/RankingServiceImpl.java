package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.dto.TeamRanking;
import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.model.Game;
import com.example.gestion_championnat.model.Team;
import com.example.gestion_championnat.service.RankingService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingServiceImpl implements RankingService {

    public List<TeamRanking> calculateRanking(Championship championship) {
        List<Team> teams = championship.getTeams();
        List<Game> allGames = championship.getDays().stream()
            .flatMap(day -> day.getGames().stream())
            .toList();

        Map<Long, TeamRanking> rankings = new HashMap<>();

        for (Team team : teams) {
            rankings.put(team.getId(), new TeamRanking());
            TeamRanking tr = rankings.get(team.getId());
            tr.setTeam(team);
            tr.setForm(new ArrayList<>());
        }

        for (Game game : allGames) {
            Team team1 = game.getTeam1();
            Team team2 = game.getTeam2();
            int score1 = game.getTeam1Point();
            int score2 = game.getTeam2Point();

            updateStats(rankings.get(team1.getId()), score1, score2, championship);
            updateStats(rankings.get(team2.getId()), score2, score1, championship);
        }

        // Optionnel : Trier les formes par ordre chronologique
        rankings.values().forEach(tr -> {
            List<String> form = tr.getForm();
            if (form.size() > 5) {
                tr.setForm(form.subList(form.size() - 5, form.size()));
            }
        });

        // Tri par points (et goalDiff, etc.)
        return rankings.values().stream()
                .sorted(Comparator.comparing(TeamRanking::getPoints)
                        .thenComparing(TeamRanking::getGoalDifference).reversed()
                        .thenComparing(TeamRanking::getGoalsFor, Comparator.reverseOrder()))
                .toList();
    }

    private void updateStats(TeamRanking tr, int goalsFor, int goalsAgainst, Championship champ) {
        tr.setPlayed(tr.getPlayed() + 1);
        tr.setGoalsFor(tr.getGoalsFor() + goalsFor);
        tr.setGoalsAgainst(tr.getGoalsAgainst() + goalsAgainst);
        tr.setGoalDifference(tr.getGoalsFor() - tr.getGoalsAgainst());

        if (goalsFor > goalsAgainst) {
            tr.setWon(tr.getWon() + 1);
            tr.setPoints(tr.getPoints() + champ.getWonPoints());
            tr.getForm().add("W");
        } else if (goalsFor == goalsAgainst) {
            tr.setDraw(tr.getDraw() + 1);
            tr.setPoints(tr.getPoints() + champ.getDrawPoints());
            tr.getForm().add("D");
        } else {
            tr.setLost(tr.getLost() + 1);
            tr.setPoints(tr.getPoints() + champ.getLostPoints());
            tr.getForm().add("L");
        }
    }
}

