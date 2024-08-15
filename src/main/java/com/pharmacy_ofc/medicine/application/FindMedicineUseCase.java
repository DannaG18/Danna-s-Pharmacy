package com.pharmacy_ofc.medicine.application;

import java.util.Optional;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;
import com.pharmacy_ofc.medicine.domain.service.MedicineService;

public class FindMedicineUseCase {
    private MedicineService medicineService;

    public FindMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public Optional<Medicine> execute(int id) {
        return medicineService.findMedicine(id);
    }
}
