package com.pharmacy_ofc.region.domain.service;

import java.util.Optional;

import com.pharmacy_ofc.region.domain.entity.Region;

public interface RegionService {
    void createRegion (Region region);
    Optional<Region> findRegionById (String code);
    void updateRegion (Region region);
    void deleteRegion (String code);
}
