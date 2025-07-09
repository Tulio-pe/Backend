package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllWorkshopsByDistrictIdQuery;

import java.util.List;
import java.util.Optional;

public interface WorkshopQueryService {
    Optional<Workshop> handle (GetWorkshopByNameQuery query);
    List<Workshop> handle (GetAllWorkshopsByDistrictIdQuery query);
}
