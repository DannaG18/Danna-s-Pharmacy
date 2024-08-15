package com.pharmacy_ofc.pharmacy.application;

import com.pharmacy_ofc.pharmacy.domain.service.PharmacyService;

public class DeletePharmacyUseCase {
    private PharmacyService pharmacyService;

    public DeletePharmacyUseCase(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }
    
    public void execute(int id) {
        pharmacyService.deletePharmacy(id);
    }
}
