package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetServiceByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceQueryServiceImpl implements ServiceQueryService {
    private final ServiceRepository serviceRepository;
            public ServiceQueryServiceImpl(ServiceRepository serviceRepository){
          this.serviceRepository= serviceRepository;
            }

    @Override
    public Optional<Service> handle(GetServiceByNameQuery query) {
        return serviceRepository.findByName(query.workshopServices());
    }
}
