package com.example.appdevgproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Planet name is required")
    @Column(unique = true, nullable = false)
    private String name;

    @Positive(message = "Radius must be positive")
    private double radius;

    @Positive(message = "Mass must be positive")
    private double mass;

    @OneToMany(mappedBy = "planet", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Moon> moons;
}
