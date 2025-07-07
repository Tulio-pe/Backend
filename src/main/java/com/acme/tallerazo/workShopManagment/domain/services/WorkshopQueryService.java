package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllRegions;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;

import java.util.List;
import java.util.Optional;

public interface WorkshopQueryService {
    Optional<Workshop> handle (GetWorkshopByNameQuery query);
}
