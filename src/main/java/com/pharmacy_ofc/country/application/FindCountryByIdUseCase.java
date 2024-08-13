package com.pharmacy_ofc.country.application;

import java.util.Optional;

import com.pharmacy_ofc.country.domain.entity.Country;
import com.pharmacy_ofc.country.domain.service.CountryService;

public class FindCountryByIdUseCase {
    private CountryService countryService;

    public FindCountryByIdUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public Optional<Country> execute(String code) {
        return countryService.findCountryById(code);
    }
}
