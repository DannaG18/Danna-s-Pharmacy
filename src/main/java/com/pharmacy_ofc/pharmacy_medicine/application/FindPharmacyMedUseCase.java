package com.pharmacy_ofc.pharmacy_medicine.application;

import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;
import com.pharmacy_ofc.pharmacy_medicine.domain.service.PharmacyMedService;
import java.util.Optional;

public class FindPharmacyMedUseCase {
        private PharmacyMedService pharmacyMedService;

    public FindPharmacyMedUseCase(PharmacyMedService pharmacyMedService) {
        this.pharmacyMedService = pharmacyMedService;
    }
    
    public Optional<PharmacyMed> execute(int id_medicine, int id_pharmacy) {
        return pharmacyMedService.findPharmacyMed(id_medicine, id_pharmacy);
    }
}
