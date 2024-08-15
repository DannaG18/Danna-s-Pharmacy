package com.pharmacy_ofc.city.application;

import com.pharmacy_ofc.city.domain.entity.City;
import com.pharmacy_ofc.city.domain.service.CityService;

public class UpdateCityUseCase {
    private CityService cityService;

    public UpdateCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(City city) {
        cityService.updateCity(city);
    }
}
