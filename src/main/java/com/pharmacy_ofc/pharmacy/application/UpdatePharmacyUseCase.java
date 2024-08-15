package com.pharmacy_ofc.pharmacy.application;

import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;

public class UpdatePharmacyUseCase {
    private PharmacyService pharmacyService;

    public UpdatePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }
    
    public void execute(Pharmacy pharmacy) {
        pharmacyService.updatePharmacy(pharmacy);
    }
}
