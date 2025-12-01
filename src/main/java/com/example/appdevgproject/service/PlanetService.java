package com.example.appdevgproject.service;

import com.example.appdevgproject.dto.CreatePlanetDto;
import com.example.appdevgproject.dto.MoonDto;
import com.example.appdevgproject.dto.PlanetDto;
import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public List<PlanetDto> getAllPlanets() {
        return planetRepository.findAll()
                .stream()
                .map(this::toPlanetDto)
                .toList();
    }

    public PlanetDto getPlanetById(Long id) {
        Planet planet = planetRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Planet not found with id " + id));
        return toPlanetDto(planet);
    }

    // mapping helpers
    private PlanetDto toPlanetDto(Planet planet) {
        List<MoonDto> moons = planet.getMoons() == null ? List.of() :
                planet.getMoons().stream()
                        .map(this::toMoonDto)
                        .toList();
        return new PlanetDto(
                planet.getId(),
                planet.getName(),
                planet.getRadius(),
                planet.getMass(),
                moons
        );
    }

    private MoonDto toMoonDto(Moon moon) {
        return new MoonDto(
                moon.getId(),
                moon.getName(),
                moon.getRadius(),
                moon.getMass()
        );
    }

    public PlanetDto createPlanet(CreatePlanetDto dto) {
        Planet planet = new Planet();
        planet.setName(dto.getName());
        planet.setRadius(dto.getRadius());
        planet.setMass(dto.getMass());

        if (dto.getMoons() != null && !dto.getMoons().isEmpty()) {
            List<Moon> moons = dto.getMoons().stream()
                    .map(mDto -> {
                        Moon m = new Moon();
                        m.setName(mDto.getName());
                        m.setRadius(mDto.getRadius());
                        m.setMass(mDto.getMass());
                        m.setPlanet(planet);
                        return m;
                    })
                    .toList();
            planet.setMoons(moons);
        }

        Planet saved = planetRepository.save(planet);
        return toPlanetDto(saved);
    }

    public Planet savePlanet(Planet planet) {
        return planetRepository.save(planet);
    }

    public void deletePlanet(Long id) {
        planetRepository.deleteById(id);
    }

    //custom queries below
    public List<PlanetDto> getPlanetByName(String name) {
        return planetRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toPlanetDto)   // reuse your mapper
                .toList();
    }


    public Double getPlanetRadiusById(Long id) {
        return planetRepository.findById(id).map(Planet::getRadius).orElse(null);
    }

    public Double getPlanetMassById(Long id) {
        return planetRepository.findById(id).map(Planet::getMass).orElse(null);
    }

    public List<PlanetDto> getPlanetsWithMinMoons(int minMoons) {
        return planetRepository.findPlanetsWithMinMoons(minMoons)
                .stream()
                .map(this::toPlanetDto)
                .toList();
    }

    public PlanetDto getPlanetWithMostMoons() {
        Planet planet = planetRepository.findPlanetWithMostMoons();
        return toPlanetDto(planet);
    }

    public PlanetDto getPlanetWithLeastMoons() {
        Planet planet = planetRepository.findPlanetWithLeastMoons();
        return toPlanetDto(planet);
    }







}
