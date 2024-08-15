package com.pharmacy_ofc.laboratory.application;

import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;

public class DeleteLaboratoryUseCase {
    private LaboratoryService laboratoryService;

    public DeleteLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(int id) {
        laboratoryService.deleteLaboratory(id);
    }
}
