package com.pharmacy_ofc.pharmacy.application;

import java.util.Optional;

import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;

public class FindPharmacyUseCase {
    private PharmacyService pharmacyService;

    public FindPharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }
    
    public Optional<Pharmacy> execute(int id) {
        return pharmacyService.findPharmacy(id);
    }
}
