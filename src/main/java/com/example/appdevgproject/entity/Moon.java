package com.example.appdevgproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Moon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Moon name is required")
    @Column(nullable = false)
    private String name;

    @Positive(message = "Radius must be positive")
    private double radius;

    @Positive(message = "Mass must be positive")
    private double mass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planet planet;
}
