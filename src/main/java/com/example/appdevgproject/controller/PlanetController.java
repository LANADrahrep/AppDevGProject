package com.example.appdevgproject.controller;

import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @GetMapping
    public List<Planet> getAllPlanets() {
        return planetService.getAllPlanets();
    }

    @GetMapping("/{id}")
    public Optional<Planet> getPlanetById(@PathVariable Long id) {
        return planetService.getPlanetById(id);
    }

    @PostMapping
    public Planet createPlanet(@RequestBody Planet planet) {
        return planetService.savePlanet(planet);
    }

    @DeleteMapping("/{id}")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }
}
