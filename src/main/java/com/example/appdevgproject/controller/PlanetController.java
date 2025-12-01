package com.example.appdevgproject.controller;

import com.example.appdevgproject.dto.CreatePlanetDto;
import com.example.appdevgproject.dto.PlanetDto;
import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.service.PlanetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // get
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping
    public List<PlanetDto> getAllPlanets() {
        return planetService.getAllPlanets();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}")
    public PlanetDto getPlanetById(@PathVariable Long id) {
        return planetService.getPlanetById(id);
    }

    // post
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public PlanetDto createPlanet(@Valid @RequestBody CreatePlanetDto dto) {
        return planetService.createPlanet(dto);
    }

    // delete
    // dto not needed as this returns 200 and no response body
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePlanet(@PathVariable Long id) {
        planetService.deletePlanet(id);
    }

    //custom queries below
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/search")
    public List<PlanetDto> searchPlanetByName(@RequestParam String name) {
        return planetService.getPlanetByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-min-moons")
    public List<PlanetDto> planetsWithMinMoons(@RequestParam int minMoons) {
        return planetService.getPlanetsWithMinMoons(minMoons);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-most-moons")
    public PlanetDto planetWithMostMoons() {
        return planetService.getPlanetWithMostMoons();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-least-moons")
    public PlanetDto planetWithLeastMoons() {
        return planetService.getPlanetWithLeastMoons();
    }

    // dtos not needed as these methods don't return entities, only primitives
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/radius")
    public Double getPlanetRadius(@PathVariable Long id) {
        return planetService.getPlanetRadiusById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/mass")
    public Double getPlanetMass(@PathVariable Long id) {
        return planetService.getPlanetMassById(id);
    }

}
