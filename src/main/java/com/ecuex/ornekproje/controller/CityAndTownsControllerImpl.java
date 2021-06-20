package com.ecuex.ornekproje.controller;

import com.ecuex.ornekproje.model.City;
import com.ecuex.ornekproje.model.Town;
import com.ecuex.ornekproje.repository.CityRepository;
import com.ecuex.ornekproje.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cityandtowns")
public class CityAndTownsControllerImpl {

    CityRepository cityRepository;
    TownRepository townRepository;

    @Autowired
    public CityAndTownsControllerImpl(CityRepository cityRepository, TownRepository townRepository) {
        this.cityRepository = cityRepository;
        this.townRepository = townRepository;
    }

    @GetMapping(value = "/gettowns/{city}", produces = "application/json")
    public List<String> getTowns(@PathVariable("city") String cityName) {
        Long cityId = cityRepository.findCityByName(cityName).orElseThrow();
        return townRepository.findTownsByCityId(cityId).orElseThrow();
    }

    @GetMapping(value = "/getcities", produces = "application/json")
    public List<String> getCities() {
        return cityRepository.findAll()
                .stream()
                .map(City::getName)
                .collect(Collectors.toList());
    }
}