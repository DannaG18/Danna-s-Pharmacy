package com.pharmacy_ofc.unit_measurement.domain.service;

import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import java.util.Optional;

public interface UnitMeasurementService {
    void createUnitMeasurement (UnitMeasurement unitMeasurement);
    Optional<UnitMeasurement> findUnitMeasurement (int id);
    void updateUnitMeasurement (UnitMeasurement unitMeasurement);
    void deleteUnitMeasurement (int id);
}
