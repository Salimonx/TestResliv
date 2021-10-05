package com.example.javatestresliv.controller;

import com.example.javatestresliv.dto.City;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @GetMapping()
    public ResponseEntity<City> get() {
        return ResponseEntity.ok(new City());
    }

}
