package com.acme.tallerazo.workShopManagment.application.internal.commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopServices;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceCommandService;

import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;

import java.util.Arrays;

/**
 * <summary>
 * Spring service that handles the seeding of initial WorkshopServices into the database.
 * </summary>
 */
@org.springframework.stereotype.Service
public class ServiceCommandServiceImpl implements ServiceCommandService {
    private final ServiceRepository serviceRepository;
    /**
     * <summary>
     * Constructs a new ServiceCommandServiceImpl with the given repository.
     * </summary>
     * <param name="serviceRepository">
     *   Repository used for checking existence and saving Service entities.
     * </param>
     */
    public ServiceCommandServiceImpl(ServiceRepository serviceRepository){
      this.serviceRepository=serviceRepository;
  }

    /**
     * <summary>
     * Processes the SeedServicesCommand by iterating over all WorkshopServices enum values.
     * For each value, checks if a Service with that name exists; if not, it saves a new one.
     * </summary>
     * <param name="command">
     *   Command object (unused currently) that triggers the seeding operation.
     * </param>
     */
    @Override
    public void handle(SeedServicesCommand command) {
        Arrays.stream(WorkshopServices.values()).forEach(service->{
            if (!serviceRepository.existsByName(service)){
                serviceRepository.save(new Service(WorkshopServices.valueOf(service.name())));
            }
        });
    }
}
