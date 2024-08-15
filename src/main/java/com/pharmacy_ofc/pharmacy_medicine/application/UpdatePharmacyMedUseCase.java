package com.pharmacy_ofc.pharmacy_medicine.application;

import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;

public class UpdatePharmacyMedUseCase {
    private PharmacyMedService pharmacyMedService;

    public UpdatePharmacyMedUseCase(PharmacyMedService pharmacyMedService) {
        this.pharmacyMedService = pharmacyMedService;
    }
    
    public void execute(PharmacyMed pharmacyMed) {
        pharmacyMedService.updatePharmacyMed(pharmacyMed);
    }
}
