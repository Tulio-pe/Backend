package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllRegions;

import java.util.Optional;

public interface RegionQueryService {
    Optional<Region> handle(GetAllRegions query);
}
