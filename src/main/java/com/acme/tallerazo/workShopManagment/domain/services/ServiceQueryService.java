package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllServicesQuery;

import java.util.List;

public interface ServiceQueryService {
 List<Service> handle (GetAllServicesQuery query) ;
}
