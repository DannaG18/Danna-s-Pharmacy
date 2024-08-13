package com.pharmacy_ofc.country.application;

import com.pharmacy_ofc.country.domain.service.CountryService;

public class DeleteCountryUseCase {
    private CountryService countryService;

    public DeleteCountryUseCase(CountryService countryService) {
        this.countryService = countryService;
    }

    public void execute(String code) {
        countryService.deleteCountry(code);
    }
}
