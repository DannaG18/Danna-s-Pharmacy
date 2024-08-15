package com.pharmacy_ofc.pharmacy.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.pharmacy.domain.entity.Pharmacy;
public interface PharmacyService {
    void createPharmacy (Pharmacy pharmacy);
    Optional<Pharmacy> findPharmacy (int id);
    void updatePharmacy (Pharmacy pharmacy);
    void deletePharmacy (int id);
}
