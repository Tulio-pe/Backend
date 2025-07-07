package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetProvincesByRegionId;

import java.util.Optional;

public interface ProvinceQueryService {
    Optional<Province> handle(GetProvincesByRegionId query);
}
