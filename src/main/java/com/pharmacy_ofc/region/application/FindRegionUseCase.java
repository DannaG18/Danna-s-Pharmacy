package com.pharmacy_ofc.region.application;

import java.util.Optional;

import com.pharmacy_ofc.region.domain.entity.Region;
import com.pharmacy_ofc.region.domain.service.RegionService;

public class FindRegionUseCase {
    private RegionService regionService;

    public FindRegionUseCase(RegionService regionService) {
        this.regionService = regionService;
    }

    public Optional<Region> execute(String code) {
        return regionService.findRegionById(code);
    }
}
