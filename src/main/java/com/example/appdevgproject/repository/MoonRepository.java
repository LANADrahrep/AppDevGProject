package com.example.appdevgproject.repository;

import com.example.appdevgproject.entity.Moon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoonRepository extends JpaRepository<Moon, Long> {

    List<Moon> findByPlanetId(Long planetId);
}
