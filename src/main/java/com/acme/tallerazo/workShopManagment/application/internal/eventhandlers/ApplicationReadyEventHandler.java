package com.acme.tallerazo.workShopManagment.application.internal.eventhandlers;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {
private final ServiceCommandService servicecommandservice;
private static final Logger LOGGER= LoggerFactory.getLogger(ApplicationReadyEventHandler.class);
    private final ServiceCommandService serviceCommandService;

    public ApplicationReadyEventHandler(ServiceCommandService servicecommandService, ServiceCommandService serviceCommandService){
     this.servicecommandservice=servicecommandService;
        this.serviceCommandService = serviceCommandService;
    }
 @EventListener
    public void on(ApplicationReadyEvent event ){
     var applicationName= event.getApplicationContext().getId();
     LOGGER.info("Starting to verify if roles seeding is needed for {} at {}", applicationName, currentTimestamp());
     var seedServicesCommand= new SeedServicesCommand();
     serviceCommandService.handle(seedServicesCommand);
     LOGGER.info("Services seeding verification finish for {} at {}", applicationName,currentTimestamp());
     }
    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
