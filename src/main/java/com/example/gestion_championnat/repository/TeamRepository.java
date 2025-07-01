package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
    @Override
    List<Team> findAll();

    @Query("SELECT t FROM Team t JOIN t.championships c WHERE c.id = :championshipId")
    Optional<List<Team>> findByChampionshipId(@Param("championshipId") Long championshipId);
}
