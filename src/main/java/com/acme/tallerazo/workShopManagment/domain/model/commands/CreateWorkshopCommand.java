package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopLocation;

import java.util.List;
import java.util.Set;

public record CreateWorkshopCommand(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        Long districtId,
        String workshopEmail,
        String photo,
        String workshopDescription,
        Long managerId,
        List<Service> services,
        List<ScheduleEntry> schedule // si deseas incluir el schedule
) {}
