package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictById;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictsByProvinceId;

import java.util.List;
import java.util.Optional;

public interface DistrictQueryService {
    Optional<District> handle(GetDistrictById query);
    List<District> handle(GetDistrictsByProvinceId query);
}
