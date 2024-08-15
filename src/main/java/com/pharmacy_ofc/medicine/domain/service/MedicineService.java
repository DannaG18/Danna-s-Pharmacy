package com.pharmacy_ofc.medicine.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;

public interface MedicineService {
    void createMedicine (Medicine medicine);
    Optional<Medicine> findMedicine (int id);
    void updateMedicine (Medicine medicine);
    void deleteMedicine (int id);
}
