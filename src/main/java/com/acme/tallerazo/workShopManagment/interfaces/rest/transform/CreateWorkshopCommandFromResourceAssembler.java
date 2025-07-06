package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.CreateWorkshopResource;

import java.util.ArrayList;

public class CreateWorkshopCommandFromResourceAssembler {
    public static CreateWorkshopCommand toCommandFromResource(CreateWorkshopResource resource){
        var services=resource.services()!=null? resource.services().stream().map(name -> Service.ToServiceFromName(name)).toList(): new ArrayList<Service>();
        return new CreateWorkshopCommand(resource.workshopName(),resource.workshopPhone(),resource.workshopAddress(),resource.Email(),resource.photo(),resource.Description(),services);
    }
}
