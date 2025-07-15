package com.example.gestion_championnat.service;

import com.example.gestion_championnat.model.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();

    Game getById(Long id) throws Exception;

    void save(Game game);

    void deleteById(Long id);
}
