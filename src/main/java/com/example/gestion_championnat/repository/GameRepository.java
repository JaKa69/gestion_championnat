package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    @Override
    List<Game> findAll();
}
