package com.pharmacy_ofc.unit_measurement.application;

import java.util.Optional;

import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;

public class FindUnitMeasurementUseCase {
        private UnitMeasurementService unitMeasurementService;

    public FindUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public Optional<UnitMeasurement> execute(int id) {
        return unitMeasurementService.findUnitMeasurement(id);
    }
}
