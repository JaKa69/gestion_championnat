package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Stadium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadiumRepository extends CrudRepository<Stadium, Long> {
    @Override
    List<Stadium> findAll();
}
