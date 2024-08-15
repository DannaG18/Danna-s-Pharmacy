package com.pharmacy_ofc.city.application;

import com.pharmacy_ofc.city.domain.entity.City;
import com.pharmacy_ofc.city.domain.service.CityService;

public class CreateCityUseCase {
    private CityService cityService;

    public CreateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city) {
        cityService.createCity(city);
    }
}
