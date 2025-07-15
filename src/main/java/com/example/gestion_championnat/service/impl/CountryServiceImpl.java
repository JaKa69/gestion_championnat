package com.example.gestion_championnat.service.impl;

import com.example.gestion_championnat.model.Country;
import com.example.gestion_championnat.repository.CountryRepository;
import com.example.gestion_championnat.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
