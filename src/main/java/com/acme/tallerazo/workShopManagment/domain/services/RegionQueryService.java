package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllRegionsQuery;

import java.util.List;

public interface RegionQueryService {
    List<Region> handle(GetAllRegionsQuery query);

}
