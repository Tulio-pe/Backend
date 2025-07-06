package com.acme.tallerazo.workShopManagment.application.internal.commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopServices;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceCommandService;

import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;

import java.util.Arrays;


@org.springframework.stereotype.Service
public class ServiceCommandServiceImpl implements ServiceCommandService {
    private final ServiceRepository servicerepository;
  public ServiceCommandServiceImpl(ServiceRepository serviceRepository){
      this.servicerepository=serviceRepository;
  }
    @Override
    public void handle(SeedServicesCommand command) {
        Arrays.stream(WorkshopServices.values()).forEach(service->{
            if (!servicerepository.existsByName(service)){
                servicerepository.save(new Service(WorkshopServices.valueOf(service.name())));
            }
        });
    }
}
