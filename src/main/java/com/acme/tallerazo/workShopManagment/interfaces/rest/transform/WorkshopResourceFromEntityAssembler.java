package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;


import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;

public class WorkshopResourceFromEntityAssembler {
    public static WorkshopResource ToResourceFromEntity(Workshop workshop){
        var services=workshop.getServices().stream().map(Service::getStringName).toList();
   return new WorkshopResource (workshop.getWorkshopName(),workshop.getWorkshopLocation(),workshop.getWorkshopPhone(),workshop.getPhoto(),workshop.getDescription(), services);
    }
}
