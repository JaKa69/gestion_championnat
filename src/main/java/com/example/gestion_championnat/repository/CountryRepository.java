package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Override
    List<Country> findAll();
}
