package com.pharmacy_ofc.city.application;

import com.pharmacy_ofc.city.domain.service.CityService;

public class DeleteCityUseCase {
    private CityService cityService;

    public DeleteCityUseCase(CityService cityService) {
        this.cityService = cityService;
    }

    public void execute(String code) {
        cityService.deleteCity(code);
    }
}
