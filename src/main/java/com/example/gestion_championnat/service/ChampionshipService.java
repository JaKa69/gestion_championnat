package com.example.gestion_championnat.service;

import com.example.gestion_championnat.model.Championship;

import java.util.List;

public interface ChampionshipService {
    Championship getById(Long id) throws Exception;
    List<Championship> getAllChampionships();
}
