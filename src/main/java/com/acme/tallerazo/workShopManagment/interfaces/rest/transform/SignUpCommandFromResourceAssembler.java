package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.SignUpCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopSignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(WorkshopSignUpResource resource){
        var services=resource.services()!=null? resource.services().stream().map(name -> WorkshopAndService.ToServiceFromName(name)).toList(): new ArrayList<WorkshopAndService>();
        return new SignUpCommand(resource.workshopName(),resource.workshopPhone(),resource.workshopAddress(),resource.Email(),resource.photo(),resource.Description(),services);
    }
}
