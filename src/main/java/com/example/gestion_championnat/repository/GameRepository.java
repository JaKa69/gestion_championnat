package com.example.gestion_championnat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.gestion_championnat.model.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    @Override
    List<Game> findAll();

    @Override
    Optional<Game> findById(Long aLong);

    Optional<List<Game>> findByDayChampionshipId(Long championshipId);

    Optional<List<Game>> findByDayId(Long dayId);
}
