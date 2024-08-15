package com.pharmacy_ofc.active_principle.application;

import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;
import com.pharmacy_ofc.active_principle.domain.service.APService;

public class CreateAPUseCase {
    private APService apService;

    public CreateAPUseCase(APService apService) {
        this.apService = apService;
    }

    public void execute(ActivePrinciple actP) {
        apService.createAP(actP);
    }
}
