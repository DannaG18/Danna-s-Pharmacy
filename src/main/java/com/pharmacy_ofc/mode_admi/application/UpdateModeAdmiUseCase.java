package com.pharmacy_ofc.mode_admi.application;

import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;
import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;

public class UpdateModeAdmiUseCase {
    private ModeAdmiService modeAdmiService;

    public UpdateModeAdmiUseCase(ModeAdmiService modeAdmiService) {
        this.modeAdmiService = modeAdmiService;
    }

    public void execute(ModeAdmi modeAdmi) {
        modeAdmiService.updateModeAdmi(modeAdmi);
    }
}
