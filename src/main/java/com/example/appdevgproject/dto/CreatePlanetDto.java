package com.example.appdevgproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePlanetDto {
    @NotBlank
    private String name;

    @Positive
    private double radius;

    @Positive
    private double mass;

    private List<CreateMoonDto> moons;
}

