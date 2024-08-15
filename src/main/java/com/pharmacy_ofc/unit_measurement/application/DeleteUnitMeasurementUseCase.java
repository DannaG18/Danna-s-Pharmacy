package com.pharmacy_ofc.unit_measurement.application;

import com.pharmacy_ofc.unit_measurement.domain.service.UnitMeasurementService;

public class DeleteUnitMeasurementUseCase {
        private UnitMeasurementService unitMeasurementService;

    public DeleteUnitMeasurementUseCase(UnitMeasurementService unitMeasurementService) {
        this.unitMeasurementService = unitMeasurementService;
    }

    public void execute(int id) {
        unitMeasurementService.deleteUnitMeasurement(id);
    }
}
