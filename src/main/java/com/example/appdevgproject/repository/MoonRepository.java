package com.example.appdevgproject.repository;

import com.example.appdevgproject.entity.Moon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long> {

    List<Moon> findByPlanetId(Long planetId);
    //custom queries below
    List<Moon> findByNameContainingIgnoreCase(String name);

    @Query("SELECT m FROM Moon m WHERE m.planet.radius = (SELECT MAX(p.radius) FROM Planet p)")
    List<Moon> findMoonsOfPlanetWithMaxRadius();

    @Query("SELECT m FROM Moon m WHERE m.planet.radius = (SELECT MIN(p.radius) FROM Planet p)")
    List<Moon> findMoonsOfPlanetWithMinRadius();

    @Query("SELECT m FROM Moon m WHERE m.planet.mass = (SELECT MAX(p.mass) FROM Planet p)")
    List<Moon> findMoonsOfPlanetWithMaxMass();

    @Query("SELECT m FROM Moon m WHERE m.planet.mass = (SELECT MIN(p.mass) FROM Planet p)")
    List<Moon> findMoonsOfPlanetWithMinMass();







}
