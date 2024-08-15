package com.pharmacy_ofc.laboratory.application;

import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;
import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;

public class CreateLaboratoryUseCase {
    private LaboratoryService laboratoryService;

    public CreateLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public void execute(Laboratory laboratory) {
        laboratoryService.createLaboratory(laboratory);
    }
}
