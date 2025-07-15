package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Game;
import com.example.gestion_championnat.repository.GameRepository;
import com.example.gestion_championnat.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getById(Long id) throws Exception {
        return gameRepository.findById(id).orElseThrow(() -> new Exception("Game not found with id: " + id));
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
