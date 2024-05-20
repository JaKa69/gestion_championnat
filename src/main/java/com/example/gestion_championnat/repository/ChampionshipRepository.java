package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Championship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Long>{

    @Override
    List<Championship> findAll();

    @Override
    Optional<Championship> findById(Long aLong);
}
