package com.pharmacy_ofc.active_principle.application;

import java.util.Optional;

import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;
import com.pharmacy_ofc.active_principle.domain.service.APService;

public class FindAPByIdUseCase {
            private APService apService;

    public FindAPByIdUseCase(APService apService) {
        this.apService = apService;
    }

    public Optional<ActivePrinciple> execute(int id) {
        return apService.findAPById(id);
    }
}
