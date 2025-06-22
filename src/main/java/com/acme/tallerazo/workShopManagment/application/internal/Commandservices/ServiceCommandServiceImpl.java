package com.acme.tallerazo.workShopManagment.application.internal.Commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;

import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkServices;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceCommandService;

import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class ServiceCommandServiceImpl implements ServiceCommandService {
    private final ServiceRepository servicerepository;
  public ServiceCommandServiceImpl(ServiceRepository serviceRepository){
      this.servicerepository=serviceRepository;
  }
    @Override
    public void handle(SeedServicesCommand command) {
        Arrays.stream(WorkServices.values()).forEach(service->{
            if (!servicerepository.existsByName(service)){
                servicerepository.save(new WorkshopAndService(WorkServices.valueOf(service.name())));
            }
        });
    }
}
