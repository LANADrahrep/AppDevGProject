package com.example.appdevgproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanetDto {
    private Long id;
    private String name;
    private double radius;
    private double mass;
    private List<MoonDto> moons;
}

