package com.pharmacy_ofc.region.application;

import com.pharmacy_ofc.region.domain.service.RegionService;

public class DeleteRegionUseCase {
    private RegionService regionService;

    public DeleteRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(String code) {
        regionService.deleteRegion(code);
    }
}
