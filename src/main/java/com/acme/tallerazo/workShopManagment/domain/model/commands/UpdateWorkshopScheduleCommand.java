package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;

import java.util.List;

public record UpdateWorkshopScheduleCommand(
        Long workshopId,
        List<ScheduleEntry> schedule
) {}