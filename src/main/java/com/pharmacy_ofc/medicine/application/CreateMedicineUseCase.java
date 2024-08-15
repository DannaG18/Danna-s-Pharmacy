package com.pharmacy_ofc.medicine.application;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;
import com.pharmacy_ofc.medicine.domain.service.MedicineService;

public class CreateMedicineUseCase {
    private MedicineService medicineService;

    public CreateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine) {
        medicineService.createMedicine(medicine);
    }
}
