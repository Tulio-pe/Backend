package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;


import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;

public class WorkshopResourceFromEntityAssembler {
    public static WorkshopResource ToResourceFromEntity(Workshop workshop){
        var services=workshop.getServices().stream().map(WorkshopAndService::getStringName).toList();
   return new WorkshopResource (workshop.getWorkshopName(),workshop.getWorkshopAddress(),workshop.getWorkshopPhone(),workshop.getPhoto(),workshop.getDescription(),services);
    }
}
