package com.pharmacy_ofc.pharmacy.application;

import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;

public class CreatePharmacyUseCase {
    private PharmacyService pharmacyService;

    public CreatePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }
    
    public void execute(Pharmacy pharmacy) {
        pharmacyService.createPharmacy(pharmacy);
    }
}
