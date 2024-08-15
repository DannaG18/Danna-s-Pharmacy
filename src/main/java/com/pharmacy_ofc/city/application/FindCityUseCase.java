package com.pharmacy_ofc.city.application;

import java.util.Optional;

import com.pharmacy_ofc.city.domain.entity.City;
import com.pharmacy_ofc.city.domain.service.CityService;

public class FindCityUseCase {
    private CityService cityService;

    public FindCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public Optional<City> execute(String code) {
        return cityService.findCity(code);
    }
}
