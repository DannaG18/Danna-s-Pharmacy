package com.pharmacy_ofc.medicine.application;

import com.pharmacy_ofc.medicine.domain.entity.Medicine;
import com.pharmacy_ofc.medicine.domain.service.MedicineService;

public class UpdateMedicineUseCase {
    private MedicineService medicineService;

    public UpdateMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(Medicine medicine) {
        medicineService.updateMedicine(medicine);
    }
}
