package com.example.appdevgproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoonDto {
    private Long id;
    private String name;
    private double radius;
    private double mass;
}
