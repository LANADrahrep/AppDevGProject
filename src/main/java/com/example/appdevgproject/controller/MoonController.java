package com.example.appdevgproject.controller;

import com.example.appdevgproject.dto.CreateMoonDto;
import com.example.appdevgproject.dto.MoonDto;
import com.example.appdevgproject.service.MoonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/moons")
public class MoonController {

    private final MoonService moonService;

    @Autowired
    public MoonController(MoonService moonService) {
        this.moonService = moonService;
    }

    // get
    @Operation(
            summary = "Get all moons",
            description = "Returns all moons with their fields."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping
    public List<MoonDto> getAllMoons() {
        return moonService.getAllMoons();
    }

    @Operation(
            summary = "Get moon by ID",
            description = "Retrieves a single moon by its ID."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}")
    public MoonDto getMoonById(@Parameter(description = "ID of the moon to retrieve") @PathVariable Long id) {
        return moonService.getMoonById(id);
    }

    // post
    @Operation(
            summary = "Create a new moon",
            description = "Creates a moon attached to an existing planet identified by planetId."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public MoonDto createMoon(@Valid @RequestBody CreateMoonDto dto) {
        return moonService.createMoon(dto);
    }

    // delete
    @Operation(
            summary = "Delete a moon",
            description = "Deletes a moon by its ID. ADMIN only."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteMoon(@Parameter(description = "ID of the moon to delete") @PathVariable Long id) {
        moonService.deleteMoon(id);
    }

    // custom queries below
    @Operation(
            summary = "Search moons by name",
            description = "Finds moons whose names match the given text ( not case-sensitive )."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/name/{name}")
    public List<MoonDto> getMoonByName(@Parameter(description = "Name of the moon") @PathVariable String name) {
        return moonService.getMoonByName(name);
    }

    @Operation(
            summary = "Get moons by planet ID",
            description = "Returns all moons that orbit the planet with the specified planetId."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/planet/{planetId}")
    public List<MoonDto> getMoonsByPlanetId(@Parameter(description = "ID of the planet whose moons to list") @PathVariable Long planetId) {
        return moonService.getMoonsByPlanetId(planetId);
    }

    @Operation(
            summary = "Moons of planet with max radius",
            description = "Returns moons that orbit the planet with the greatest radius."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-radius")
    public List<MoonDto> moonsOfPlanetWithMaxRadius() {
        return moonService.getMoonsOfPlanetWithMaxRadius();
    }

    @Operation(
            summary = "Moons of planet with min radius",
            description = "Returns moons that orbit the planet with the smallest radius."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-radius")
    public List<MoonDto> moonsOfPlanetWithMinRadius() {
        return moonService.getMoonsOfPlanetWithMinRadius();
    }

    @Operation(
            summary = "Moons of planet with max mass",
            description = "Returns moons that orbit the planet with the greatest mass."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-max-mass")
    public List<MoonDto> moonsOfPlanetWithMaxMass() {
        return moonService.getMoonsOfPlanetWithMaxMass();
    }

    @Operation(
            summary = "Moons of planet with min mass",
            description = "Returns moons that orbit the planet with the smallest mass."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/of-planet-with-min-mass")
    public List<MoonDto> moonsOfPlanetWithMinMass() {
        return moonService.getMoonsOfPlanetWithMinMass();
    }

}
