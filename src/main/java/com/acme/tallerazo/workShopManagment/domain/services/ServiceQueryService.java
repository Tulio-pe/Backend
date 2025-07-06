package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetServiceByNameQuery;

import java.util.Optional;

public interface ServiceQueryService {
 Optional<Service>handle (GetServiceByNameQuery query) ;
}
