package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Championship;
import com.example.gestion_championnat.repository.ChampionshipRepository;
import com.example.gestion_championnat.service.ChampionshipService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {
    private final ChampionshipRepository championshipRepository;

    public ChampionshipServiceImpl(ChampionshipRepository championshipRepository) {
        this.championshipRepository = championshipRepository;
    }

    @Override
    public Championship getById(Long id) throws Exception {
        return championshipRepository.findById(id).orElseThrow(() -> new Exception("Team with id: " + id + " not found"));
    }

    @Override
    public List<Championship> getAllChampionships() {
        return championshipRepository.findAll();
    }

    @Override
    public Championship save(Championship championship) {
        return championshipRepository.save(championship);
    }

    @Override
    public void delete(Long championshipId) {
        championshipRepository.deleteById(championshipId);
    }
}
