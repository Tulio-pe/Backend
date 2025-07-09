package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.DayOfWeek;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopLocation;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.CreateWorkshopResource;


import java.util.ArrayList;
import java.util.List;

public class CreateWorkshopCommandFromResourceAssembler {

    public static CreateWorkshopCommand toCommandFromResource(CreateWorkshopResource resource){
        List<Service> services = resource.services() != null
                ? resource.services().stream()
                .map(Service::ToServiceFromName)
                .toList()
                : new ArrayList<>();

        List<ScheduleEntry> schedule = resource.schedule() != null
                ? resource.schedule().stream()
                .map(entry -> new ScheduleEntry(
                        entry.startTime(),
                        entry.endTime(),
                        DayOfWeek.valueOf(entry.dayOfWeek().toUpperCase())
                ))
                .toList()
                : new ArrayList<>();



        return new CreateWorkshopCommand(
                resource.workshopName(),
                resource.workshopPhone(),
                resource.workshopAddress(),
                resource.districtId(),
                resource.workshopEmail(),   // nombre corregido
                resource.photo(),
                resource.description(),
                resource.managerId(),
                services,
                schedule
        );
    }
}

