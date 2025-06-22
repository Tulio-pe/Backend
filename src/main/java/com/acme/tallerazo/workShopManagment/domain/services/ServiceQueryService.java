package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetServiceByNameQuery;

import java.util.Optional;

public interface ServiceQueryService {
 Optional<WorkshopAndService>handle (GetServiceByNameQuery query) ;
}
