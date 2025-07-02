package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Day;
import com.example.gestion_championnat.repository.DayRepository;
import com.example.gestion_championnat.service.DayService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DayServiceImpl implements DayService {
    private final DayRepository dayRepository;

    public DayServiceImpl(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Override
    public List<Day> getAllDays() {
        return dayRepository.findAll();
    }

    @Override
    public Day getById(Long id) throws Exception {
        return dayRepository.findById(id).orElseThrow(() -> new Exception("day not found with id: " + id));
    }
}
