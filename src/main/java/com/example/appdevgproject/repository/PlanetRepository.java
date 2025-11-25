package com.example.appdevgproject.repository;

import com.example.appdevgproject.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

    Optional<Planet> findByName(String name);

    boolean existsByName(String name);
    //custom queries below
    List<Planet> findByNameContainingIgnoreCase(String name);

    @Query("SELECT p FROM Planet p WHERE size(p.moons) >= :minMoons")
    List<Planet> findPlanetsWithMinMoons(@Param("minMoons") int minMoons);

    @Query("SELECT p FROM Planet p WHERE size(p.moons) = (SELECT MAX(size(q.moons)) FROM Planet q)")
    Planet findPlanetWithMostMoons();

    @Query("SELECT p FROM Planet p WHERE size(p.moons) = (SELECT MIN(size(q.moons)) FROM Planet q)")
    Planet findPlanetWithLeastMoons();





}


