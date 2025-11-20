package com.example.appdevgproject.service;

import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.repository.MoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoonService {

    private final MoonRepository moonRepository;

    @Autowired
    public MoonService(MoonRepository moonRepository) {
        this.moonRepository = moonRepository;
    }

    public List<Moon> getAllMoons() {
        return moonRepository.findAll();
    }

    public List<Moon> getMoonsByPlanetId(Long planetId) {
        return moonRepository.findByPlanetId(planetId);
    }

    public Optional<Moon> getMoonById(Long id) {
        return moonRepository.findById(id);
    }

    public Moon saveMoon(Moon moon) {
        return moonRepository.save(moon);
    }

    public void deleteMoon(Long id) {
        moonRepository.deleteById(id);
    }
}
