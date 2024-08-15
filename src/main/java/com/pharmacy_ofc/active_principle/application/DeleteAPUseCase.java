package com.pharmacy_ofc.active_principle.application;

import com.pharmacy_ofc.active_principle.domain.service.APService;

public class DeleteAPUseCase {
            private APService apService;

    public DeleteAPUseCase(APService apService) {
        this.apService = apService;
    }

    public void execute(int id) {
        apService.deleteAP(id);
    }
}
