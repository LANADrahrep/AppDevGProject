package com.example.appdevgproject.controller;

import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.service.MoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/moons")
public class MoonController {

    private final MoonService moonService;

    @Autowired
    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping
    public List<Moon> getAllMoons() {
        return moonService.getAllMoons();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}")
    public Optional<Moon> getMoonById(@PathVariable Long id) {
        return moonService.getMoonById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/planet/{planetId}")
    public List<Moon> getMoonsByPlanetId(@PathVariable Long planetId) {
        return moonService.getMoonsByPlanetId(planetId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public Moon createMoon(@RequestBody Moon moon) {
        return moonService.saveMoon(moon);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMoon(@PathVariable Long id) {
        moonService.deleteMoon(id);
    }

    //custom queries below
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/name/{name}")
    public List<Moon> getMoonByName(@PathVariable String name) {
        return moonService.getMoonByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/radius")
    public Double getMoonRadius(@PathVariable Long id) {
        return moonService.getMoonRadiusById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/mass")
    public Double getMoonMass(@PathVariable Long id) {
        return moonService.getMoonMassById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-radius")
    public List<Moon> moonsOfPlanetWithMaxRadius() {
        return moonService.getMoonsOfPlanetWithMaxRadius();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-radius")
    public List<Moon> moonsOfPlanetWithMinRadius() {
        return moonService.getMoonsOfPlanetWithMinRadius();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-mass")
    public List<Moon> moonsOfPlanetWithMaxMass() {
        return moonService.getMoonsOfPlanetWithMaxMass();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-mass")
    public List<Moon> moonsOfPlanetWithMinMass() {
        return moonService.getMoonsOfPlanetWithMinMass();
    }
}
