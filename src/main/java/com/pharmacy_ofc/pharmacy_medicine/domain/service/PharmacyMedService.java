package com.pharmacy_ofc.pharmacy_medicine.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.pharmacy_medicine.domain.entity.PharmacyMed;

public interface PharmacyMedService {
    void createPharmacyMed (PharmacyMed pharmacyMed);
    Optional<PharmacyMed> findPharmacyMed (int id_medicine, int id_pharmacy);
    void updatePharmacyMed (PharmacyMed pharmacyMed);
    void deletePharmacyMed (int id_medicine, int id_pharmacy);
}
