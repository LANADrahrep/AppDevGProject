package com.example.appdevgproject.controller;

import com.example.appdevgproject.dto.CreatePlanetDto;
import com.example.appdevgproject.dto.PlanetDto;
import com.example.appdevgproject.service.PlanetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


@RestController
@RequestMapping("/api/planets")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    // get
    @Operation(
            summary = "Get all planets",
            description = "Returns all planets with their fields and associated moons."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping
    public List<PlanetDto> getAllPlanets() {
        return planetService.getAllPlanets();
    }

    @Operation(
            summary = "Get a planet by ID",
            description = "Returns a single planet by its ID."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}")
    public PlanetDto getPlanetById(@Parameter(description = "ID of the planet to retrieve") @PathVariable Long id) {
        return planetService.getPlanetById(id);
    }

    // post
    @Operation(
            summary = "Create a new planet",
            description = "Creates a new planet, optionally with moons."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PostMapping
    public PlanetDto createPlanet(@Valid @RequestBody CreatePlanetDto dto) {
        return planetService.createPlanet(dto);
    }

    // delete
    // dto not needed as this returns 200 and no response body
    @Operation(
            summary = "Delete a planet",
            description = "Deletes a planet by its ID. ADMIN only."
    )
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePlanet(@Parameter(description = "ID of the planet to delete") @PathVariable Long id) {
        planetService.deletePlanet(id);
    }

    //custom queries below
    @Operation(
            summary = "Search planets by name",
            description = "Finds planets whose names contain the given text ( not case-sensitive )."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/search")
    public List<PlanetDto> searchPlanetByName(@Parameter(description = "Substring to search within planet names") @RequestParam String name) {
        return planetService.getPlanetByName(name);
    }

    @Operation(
            summary = "Planets with at least N moons",
            description = "Returns planets that have at least the specified number of moons."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-min-moons")
    public List<PlanetDto> planetsWithMinMoons(@Parameter(description = "Minimum number of moons a planet must have") @RequestParam int minMoons) {
        return planetService.getPlanetsWithMinMoons(minMoons);
    }

    @Operation(
            summary = "Planet with most moons",
            description = "Returns the planet that has the highest number of moons."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-most-moons")
    public PlanetDto planetWithMostMoons() {
        return planetService.getPlanetWithMostMoons();
    }

    @Operation(
            summary = "Planet with least moons",
            description = "Returns the planet that has the lowest number of moons."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/with-least-moons")
    public PlanetDto planetWithLeastMoons() {
        return planetService.getPlanetWithLeastMoons();
    }

    // dtos not needed as these methods don't return entities, only primitives
    @Operation(
            summary = "Get planet radius",
            description = "Returns the radius of a planet identified by its ID."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/radius")
    public Double getPlanetRadius(@Parameter(description = "ID of the planet") @PathVariable Long id) {
        return planetService.getPlanetRadiusById(id);
    }

    @Operation(
            summary = "Get planet mass",
            description = "Returns the mass of a planet identified by its ID."
    )
    @PreAuthorize("hasAnyRole('ADMIN','STAFF','STUDENT')")
    @GetMapping("/{id}/mass")
    public Double getPlanetMass(@Parameter(description = "ID of the planet") @PathVariable Long id) {
        return planetService.getPlanetMassById(id);
    }

}
