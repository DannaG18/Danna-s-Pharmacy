package com.pharmacy_ofc.pharmacy_medicine.application;

import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;

public class DeletePharmacyMedUseCase {
    private PharmacyMedService pharmacyMedService;

    public DeletePharmacyMedUseCase(PharmacyMedService pharmacyMedService) {
        this.pharmacyMedService = pharmacyMedService;
    }
    
    public void execute(int id_medicine, int id_pharmacy) {
        pharmacyMedService.deletePharmacyMed(id_medicine, id_pharmacy);
    }
}
