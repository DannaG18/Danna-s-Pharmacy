package com.pharmacy_ofc.mode_admi.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.mode_admi.domain.entity.ModeAdmi;

public interface ModeAdmiService {
    void createModeAdmi (ModeAdmi modeAdmi);
    Optional<ModeAdmi> findModeAdmi (int code);
    void updateModeAdmi (ModeAdmi modeAdmi);
    void deleteModeAdmi (int id);
}
