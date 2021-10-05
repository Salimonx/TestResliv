package com.example.javatestresliv.service;

import com.example.javatestresliv.dto.City;
import com.example.javatestresliv.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    CityRepository cityRepository;

    @Autowired
    CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City create(City city) {
        return cityRepository.save(city);
    }

    public City getByName(String name) {
        return cityRepository.findByName(name);
    }

    public List<City> getAll() {
        return cityRepository.findAll();
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    public City update(City city) {
        return cityRepository.save(city);
    }

}
