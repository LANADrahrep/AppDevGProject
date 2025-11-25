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
    //custom queries below
    public List<Moon> getMoonByName(String name) {
        return moonRepository.findByNameContainingIgnoreCase(name);
    }

    public Double getMoonRadiusById(Long id) {
        return moonRepository.findById(id).map(Moon::getRadius).orElse(null);
    }

    public Double getMoonMassById(Long id) {
        return moonRepository.findById(id).map(Moon::getMass).orElse(null);
    }

    public List<Moon> getMoonsOfPlanetWithMaxRadius() {
        return moonRepository.findMoonsOfPlanetWithMaxRadius();
    }

    public List<Moon> getMoonsOfPlanetWithMinRadius() {
        return moonRepository.findMoonsOfPlanetWithMinRadius();
    }

    public List<Moon> getMoonsOfPlanetWithMaxMass() {
        return moonRepository.findMoonsOfPlanetWithMaxMass();
    }

    public List<Moon> getMoonsOfPlanetWithMinMass() {
        return moonRepository.findMoonsOfPlanetWithMinMass();
    }







}
