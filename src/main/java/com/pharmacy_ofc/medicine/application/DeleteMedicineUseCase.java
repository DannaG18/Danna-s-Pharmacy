package com.pharmacy_ofc.medicine.application;

import com.pharmacy_ofc.medicine.domain.service.MedicineService;

public class DeleteMedicineUseCase {
    private MedicineService medicineService;

    public DeleteMedicineUseCase(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void execute(int id) {
        medicineService.deleteMedicine(id);
    }
}
