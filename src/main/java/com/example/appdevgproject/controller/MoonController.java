package com.example.appdevgproject.controller;

import com.example.appdevgproject.dto.CreateMoonDto;
import com.example.appdevgproject.dto.MoonDto;
import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.service.MoonService;
import jakarta.validation.Valid;
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

    // get
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping
    public List<MoonDto> getAllMoons() {
        return moonService.getAllMoons();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}")
    public MoonDto getMoonById(@PathVariable Long id) {
        return moonService.getMoonById(id);
    }

    // post
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public MoonDto createMoon(@Valid @RequestBody CreateMoonDto dto) {
        return moonService.createMoon(dto);
    }

    // delete
    // dto not needed as this returns 200 and no response body
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMoon(@PathVariable Long id) {
        moonService.deleteMoon(id);
    }

    //custom queries below
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/name/{name}")
    public List<MoonDto> getMoonByName(@PathVariable String name) {
        return moonService.getMoonByName(name);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/planet/{planetId}")
    public List<MoonDto> getMoonsByPlanetId(@PathVariable Long planetId) {
        return moonService.getMoonsByPlanetId(planetId);
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-radius")
    public List<MoonDto> moonsOfPlanetWithMaxRadius() {
        return moonService.getMoonsOfPlanetWithMaxRadius();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-radius")
    public List<MoonDto> moonsOfPlanetWithMinRadius() {
        return moonService.getMoonsOfPlanetWithMinRadius();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-mass")
    public List<MoonDto> moonsOfPlanetWithMaxMass() {
        return moonService.getMoonsOfPlanetWithMaxMass();
    }

    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-mass")
    public List<MoonDto> moonsOfPlanetWithMinMass() {
        return moonService.getMoonsOfPlanetWithMinMass();
    }

}
