package com.example.javatestresliv.controller;

import com.example.javatestresliv.dto.City;
import com.example.javatestresliv.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public ResponseEntity<City> get(@RequestParam String name) {
        return ResponseEntity.ok(cityService.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAll() {
        return ResponseEntity.ok(cityService.getAll());
    }

    @PostMapping()
    public ResponseEntity<City> post(@RequestBody City city) {
        return ResponseEntity.ok(cityService.create(city));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        cityService.deleteById(id);
    }

    @PutMapping
    public ResponseEntity<City> put(@RequestBody City city) {
        return ResponseEntity.ok(cityService.update(city));
    }

}
