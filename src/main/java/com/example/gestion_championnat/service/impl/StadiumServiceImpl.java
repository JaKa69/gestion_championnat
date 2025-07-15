package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Stadium;
import com.example.gestion_championnat.repository.StadiumRepository;
import com.example.gestion_championnat.service.StadiumService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadiumServiceImpl implements StadiumService {
    private final StadiumRepository stadiumRepository;

    public StadiumServiceImpl(StadiumRepository stadiumRepository) {
        this.stadiumRepository = stadiumRepository;
    }

    @Override
    public List<Stadium> getAllStadiums() {
        return stadiumRepository.findAll();
    }
}
