package com.example.appdevgproject.service;

import com.example.appdevgproject.dto.CreateMoonDto;
import com.example.appdevgproject.dto.MoonDto;
import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.repository.MoonRepository;
import com.example.appdevgproject.repository.PlanetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MoonService {

    private final MoonRepository moonRepository;
    private final PlanetRepository planetRepository;

    @Autowired
    public MoonService(MoonRepository moonRepository, PlanetRepository planetRepository) {
        this.moonRepository = moonRepository;
        this.planetRepository = planetRepository;
    }

    public List<MoonDto> getAllMoons() {
        return moonRepository.findAll()
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public MoonDto getMoonById(Long id) {
        Moon moon = moonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Moon not found with id " + id));
        return toMoonDto(moon);
    }

    public MoonDto createMoon(CreateMoonDto dto) {
        Planet planet = planetRepository.findById(dto.getPlanetId())
                .orElseThrow(() -> new NoSuchElementException(
                        "Planet not found with id " + dto.getPlanetId()));

        Moon moon = new Moon();
        moon.setName(dto.getName());
        moon.setRadius(dto.getRadius());
        moon.setMass(dto.getMass());
        moon.setPlanet(planet);

        Moon saved = moonRepository.save(moon);
        return toMoonDto(saved);
    }

    public void deleteMoon(Long id) {
        moonRepository.deleteById(id);
    }

    // mapper
    private MoonDto toMoonDto(Moon moon) {
        return new MoonDto(
                moon.getId(),
                moon.getName(),
                moon.getRadius(),
                moon.getMass()
        );
    }

    // custom queries below
    public List<MoonDto> getMoonByName(String name) {
        return moonRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public List<MoonDto> getMoonsByPlanetId(Long planetId) {
        return moonRepository.findByPlanetId(planetId)
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public List<MoonDto> getMoonsOfPlanetWithMaxRadius() {
        return moonRepository.findMoonsOfPlanetWithMaxRadius()
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public List<MoonDto> getMoonsOfPlanetWithMinRadius() {
        return moonRepository.findMoonsOfPlanetWithMinRadius()
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public List<MoonDto> getMoonsOfPlanetWithMaxMass() {
        return moonRepository.findMoonsOfPlanetWithMaxMass()
                .stream()
                .map(this::toMoonDto)
                .toList();
    }

    public List<MoonDto> getMoonsOfPlanetWithMinMass() {
        return moonRepository.findMoonsOfPlanetWithMinMass()
                .stream()
                .map(this::toMoonDto)
                .toList();
    }








}
