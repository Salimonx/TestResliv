package com.example.javatestresliv.service;

import com.example.javatestresliv.dto.City;
import com.example.javatestresliv.exception.BusinessException;
import com.example.javatestresliv.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CityService {

    private static final String CREATE_ERROR_MESSAGE = "City with name %s already exist";
    private static final String SELECT_FOUND_MESSAGE = "City with name %s not found";
    private static final String UPDATE_ERROR_MESSAGE = "City with id %s not found";


    CityRepository cityRepository;

    @Autowired
    CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Transactional
    public City create(City city) {
        if (cityRepository.existsByName(city.getName()))
            throw new BusinessException(format(CREATE_ERROR_MESSAGE, city.getName()), INTERNAL_SERVER_ERROR);
        return cityRepository.save(city);
    }

    public City getByName(String name) {
        return cityRepository.findByName(name).orElseThrow(() -> new BusinessException(format(SELECT_FOUND_MESSAGE, name), NOT_FOUND));
    }

    public List<City> getAll() {
        return cityRepository.findAllByOrderByIdAsc();
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }

    @Transactional
    public City update(City city) {
        if (!cityRepository.existsById(city.getId()))
            throw new BusinessException(format(UPDATE_ERROR_MESSAGE, city.getName()), NOT_FOUND);
        return cityRepository.save(city);
    }

}
