package com.example.gestion_championnat.service;

import com.example.gestion_championnat.dto.TeamRanking;
import com.example.gestion_championnat.model.Championship;

import java.util.List;

public interface RankingService {
    List<TeamRanking> calculateRanking(Championship championship);

}
