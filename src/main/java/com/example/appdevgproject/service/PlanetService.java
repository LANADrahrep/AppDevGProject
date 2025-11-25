package com.example.appdevgproject.service;

import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Optional<Planet> getPlanetById(Long id) {
        return planetRepository.findById(id);
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public void deletePlanet(Long id) {
        planetRepository.deleteById(id);
    }

    //custom queries below
    public List<Planet> getPlanetByName(String name) {
        return planetRepository.findByNameContainingIgnoreCase(name);
    }

    public Double getPlanetRadiusById(Long id) {
        return planetRepository.findById(id).map(Planet::getRadius).orElse(null);
    }

    public Double getPlanetMassById(Long id) {
        return planetRepository.findById(id).map(Planet::getMass).orElse(null);
    }

    public List<Planet> getPlanetsWithMinMoons(int minMoons) {
        return planetRepository.findPlanetsWithMinMoons(minMoons);
    }

    public Planet getPlanetWithMostMoons() {
        return planetRepository.findPlanetWithMostMoons();
    }

    public Planet getPlanetWithLeastMoons() {
        return planetRepository.findPlanetWithLeastMoons();
    }






}
