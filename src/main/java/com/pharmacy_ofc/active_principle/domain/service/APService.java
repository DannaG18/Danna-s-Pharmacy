package com.pharmacy_ofc.active_principle.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.active_principle.domain.entity.ActivePrinciple;

public interface APService {
    void createAP (ActivePrinciple actP);
    Optional<ActivePrinciple> findAPById (int id);
    void updateAP (ActivePrinciple actP);
    void deleteAP (int id);
}
