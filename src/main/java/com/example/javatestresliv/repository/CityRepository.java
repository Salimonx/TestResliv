package com.example.javatestresliv.repository;

import com.example.javatestresliv.dto.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByName(String name);

    boolean existsByName(String name);

    List<City> findAllByOrderByIdAsc();
}
