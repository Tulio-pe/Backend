package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SignUpCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopSignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(WorkshopSignUpResource resource){
        var services=resource.services()!=null? resource.services().stream().map(name -> Service.ToServiceFromName(name)).toList(): new ArrayList<Service>();
        return new SignUpCommand(resource.workshopName(),resource.workshopPhone(),resource.workshopAddress(),resource.Email(),resource.photo(),resource.Description(),services);
    }
}
