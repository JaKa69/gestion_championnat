package com.example.gestion_championnat.repository;

import com.example.gestion_championnat.model.Stadium;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends CrudRepository<Stadium, Long> {
}
