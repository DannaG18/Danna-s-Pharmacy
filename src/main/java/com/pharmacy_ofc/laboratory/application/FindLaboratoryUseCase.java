package com.pharmacy_ofc.laboratory.application;

import java.util.Optional;

import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;
import com.pharmacy_ofc.laboratory.domain.service.LaboratoryService;

public class FindLaboratoryUseCase {
    private LaboratoryService laboratoryService;

    public FindLaboratoryUseCase(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    public Optional<Laboratory> execute(int id) {
        return laboratoryService.findLaboratory(id);
    }
}
