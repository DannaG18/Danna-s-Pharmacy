package com.pharmacy_ofc.unit_measurement.application;

import com.pharmacy_ofc.unit_measurement.domain.entity.UnitMeasurement;
import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;

public class CreateUnitMeasurementUseCase {
    private UnitMeasurementService unitMeasurementService;

    public CreateUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(UnitMeasurement unitMeasurement) {
        unitMeasurementService.createUnitMeasurement(unitMeasurement);
    }
}
