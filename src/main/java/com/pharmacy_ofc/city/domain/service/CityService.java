package com.pharmacy_ofc.city.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.city.domain.entity.City;

public interface CityService {
    void createCity (City city);
    Optional<City> findCity (String code);
    void updateCity (City city);
    void deleteCity (String code);
}
