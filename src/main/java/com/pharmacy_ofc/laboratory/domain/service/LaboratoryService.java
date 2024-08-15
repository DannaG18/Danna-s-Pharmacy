package com.pharmacy_ofc.laboratory.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.laboratory.domain.entity.Laboratory;

public interface LaboratoryService {
    void createLaboratory (Laboratory laboratory);
    Optional<Laboratory> findLaboratory (int id);
    void updateLaboratory (Laboratory laboratory);
    void deleteLaboratory (int id);
}
