package com.example.appdevgproject.controller;

import com.example.appdevgproject.entity.Moon;
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
        if (planet.getMoons() != null) {
            for (Moon moon : planet.getMoons()) {
                moon.setId(null);         // Ensure it's a new entity
                moon.setPlanet(planet);   // Set parent reference
            }
        }
        return planetService.savePlanet(planet);
    }



    @DeleteMapping("/{id}")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }

    //custom queries below
    @GetMapping("/search")
    public List<Planet> searchPlanetByName(@RequestParam String name) {
        return planetService.getPlanetByName(name);
    }

    @GetMapping("/{id}/radius")
    public Double getPlanetRadius(@PathVariable Long id) {
        return planetService.getPlanetRadiusById(id);
    }

    @GetMapping("/{id}/mass")
    public Double getPlanetMass(@PathVariable Long id) {
        return planetService.getPlanetMassById(id);
    }

    @GetMapping("/with-min-moons")
    public List<Planet> planetsWithMinMoons(@RequestParam int minMoons) {
        return planetService.getPlanetsWithMinMoons(minMoons);
    }

    @GetMapping("/with-most-moons")
    public Planet planetWithMostMoons() {
        return planetService.getPlanetWithMostMoons();
    }

    @GetMapping("/with-least-moons")
    public Planet planetWithLeastMoons() {
        return planetService.getPlanetWithLeastMoons();
    }








}
