package com.pharmacy_ofc.pharmacy_medicine.application;

import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;

public class CreatePharmacyMedUseCase {
    private PharmacyMedService pharmacyMedService;

    public CreatePharmacyMedUseCase(PharmacyMedService pharmacyMedService) {
        this.pharmacyMedService = pharmacyMedService;
    }
    
    public void execute(PharmacyMed pharmacyMed) {
        pharmacyMedService.createPharmacyMed(pharmacyMed);
    }
}
