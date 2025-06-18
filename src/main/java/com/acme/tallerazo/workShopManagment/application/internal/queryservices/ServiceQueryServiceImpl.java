package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetServiceByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceQueryServiceImpl implements ServiceQueryService {
    private final ServiceRepository serviceRepository;
            public ServiceQueryServiceImpl(ServiceRepository serviceRepository){
          this.serviceRepository= serviceRepository;
            }

    @Override
    public Optional<WorkshopAndService> handle(GetServiceByNameQuery query) {
        return serviceRepository.findByName(query.workServices());
    }
}
