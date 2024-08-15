package com.pharmacy_ofc.laboratory.application;

import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;
import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;

public class UpdateLaboratoryUseCase {
    private LaboratoryService laboratoryService;

    public UpdateLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(Laboratory laboratory) {
        laboratoryService.updateLaboratory(laboratory);
    }
}
