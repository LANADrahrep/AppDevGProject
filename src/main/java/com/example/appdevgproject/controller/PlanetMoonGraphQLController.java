package com.example.appdevgproject.controller;

import com.example.appdevgproject.entity.Planet;
import com.example.appdevgproject.entity.Moon;
import com.example.appdevgproject.repository.PlanetRepository;
import com.example.appdevgproject.repository.MoonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PlanetMoonGraphQLController {
    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private MoonRepository moonRepository;

    @QueryMapping
    public List<Planet> planets() {
        return planetRepository.findAll();
    }

    @QueryMapping
    public List<Moon> moons() {
        return moonRepository.findAll();
    }
}
