package com.pharmacy_ofc.mode_admi.application;

import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;
import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;

public class CreateModeAdmiUseCase {
    private ModeAdmiService modeAdmiService;

    public CreateModeAdmiUseCase(ModeAdmiService modeAdmiService) {
        this.modeAdmiService = modeAdmiService;
    }

    public void execute(ModeAdmi modeAdmi) {
        modeAdmiService.createModeAdmi(modeAdmi);
    }
}
