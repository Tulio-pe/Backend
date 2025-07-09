package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictByIdQuery;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllDistrictsByProvinceIdQuery;

import java.util.List;
import java.util.Optional;

public interface DistrictQueryService {
    Optional<District> handle(GetDistrictByIdQuery query);
    List<District> handle(GetAllDistrictsByProvinceIdQuery query);
}
