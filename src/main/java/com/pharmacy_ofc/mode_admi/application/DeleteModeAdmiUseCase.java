package com.pharmacy_ofc.mode_admi.application;

import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;

public class DeleteModeAdmiUseCase {
        private ModeAdmiService modeAdmiService;

    public DeleteModeAdmiUseCase(ModeAdmiService modeAdmiService) {
        this.modeAdmiService = modeAdmiService;
    }

    public void execute(int id) {
        modeAdmiService.deleteModeAdmi(id);
    }
}
