package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.DayOfWeek;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.CreateWorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.UpdateWorkshopResource;

import java.util.ArrayList;
import java.util.List;

public class UpdateWorkshopCommandFromResourceAssembler {

    public static UpdateWorkshopCommand toCommandFromResource(UpdateWorkshopResource resource){
        List<Service> services = resource.services() != null
                ? resource.services().stream()
                .map(Service::ToServiceFromName)
                .toList()
                : new ArrayList<>();

        return new UpdateWorkshopCommand(
                resource.workshopName(),
                resource.workshopPhone(),
                resource.workshopAddress(),
                resource.workshopEmail(),   // nombre corregido
                resource.photo(),
                resource.workshopDescription(),
                resource.districtId(),
                resource.managerId(),
                services
        );
    }
}

