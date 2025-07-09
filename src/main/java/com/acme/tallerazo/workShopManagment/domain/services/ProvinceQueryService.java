package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllProvincesByRegionIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProvinceQueryService {
    List<Province> handle(GetAllProvincesByRegionIdQuery query);
}
