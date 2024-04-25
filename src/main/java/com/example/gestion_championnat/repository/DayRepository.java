package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DayRepository extends CrudRepository<Day, Long> {

        @Override
        List<Day> findAll();


        @Override
        Optional<Day> findById(Long aLong);

        Optional<List<Day>> findByChampionshipId(Long championshipId);
}
