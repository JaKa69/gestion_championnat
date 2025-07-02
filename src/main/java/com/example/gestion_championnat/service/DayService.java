package com.example.gestion_championnat.service;

import com.example.gestion_championnat.model.Day;

import java.util.List;

public interface DayService {
    List<Day> getAllDays();
    Day getById(Long id) throws Exception;
}
