package com.example.appdevgproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMoonDto {
    @NotBlank
    private String name;

    @Positive
    private double radius;

    @Positive
    private double mass;

    @NotNull
    private Long planetId;
}