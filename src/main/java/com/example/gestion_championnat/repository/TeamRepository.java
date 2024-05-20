package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    @Override
    List<Team> findAll();

    @Override
    Optional<Team> findById(Long aLong);

    Optional<List<Team>> findByChampionshipList_Id(Long championshipId);
}


