package com.pharmacy_ofc.country.application;

import com.pharmacy_ofc.country.domain.entity.Country;
import com.pharmacy_ofc.country.domain.service.CountryService;

public class CreateCountryUseCase {
    private CountryService countryService;

    public CreateCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(Country country) {
        countryService.createCountry(country);
    }
}
