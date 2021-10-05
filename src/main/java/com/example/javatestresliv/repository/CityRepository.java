package com.example.javatestresliv.repository;

import com.example.javatestresliv.dto.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);

}
