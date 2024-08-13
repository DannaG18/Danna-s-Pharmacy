package com.pharmacy_ofc.country.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.country.domain.entity.Country;

public interface CountryService {
    void createCountry (Country country);
    Optional<Country> findCountryById (String code);
    void updateCountry (Country country);
    void deleteCountry (String code);
}
