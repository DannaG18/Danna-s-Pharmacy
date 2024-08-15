package com.pharmacy_ofc.mode_admi.application;

import java.util.Optional;

import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;
import com.pharmacy_ofc.mode_admi.domain.service.ModeAdmiService;

public class FindModeAdmiUseCase {
        private ModeAdmiService modeAdmiService;

    public FindModeAdmiUseCase(ModeAdmiService modeAdmiService) {
        this.modeAdmiService = modeAdmiService;
    }

    public Optional<ModeAdmi> execute(int id) {
        return modeAdmiService.findModeAdmi(id);
    }
}
