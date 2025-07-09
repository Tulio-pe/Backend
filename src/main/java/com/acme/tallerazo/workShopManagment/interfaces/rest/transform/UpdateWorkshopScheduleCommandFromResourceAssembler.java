package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopScheduleCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.DayOfWeek;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.UpdateWorkshopScheduleResource;

import java.util.ArrayList;
import java.util.List;

public class UpdateWorkshopScheduleCommandFromResourceAssembler {
    public static UpdateWorkshopScheduleCommand toCommandFromResource(Long id, UpdateWorkshopScheduleResource resource) {
        List<ScheduleEntry> schedule = resource.schedule() != null
                ? resource.schedule().stream()
                .map(entry -> new ScheduleEntry(entry.startTime(), entry.endTime(), DayOfWeek.valueOf(entry.dayOfWeek().toUpperCase())))
                .toList()
                : new ArrayList<>();

        return new UpdateWorkshopScheduleCommand(
                id,
                schedule
        );
    }
}
