package com.pharmacy_ofc.region.application;

import com.pharmacy_ofc.region.domain.entity.Region;
import com.pharmacy_ofc.region.domain.service.RegionService;

public class UpdateRegionUseCase {
        private RegionService regionService;

    public UpdateRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public void execute(Region region) {
        regionService.updateRegion(region);
    }
}
