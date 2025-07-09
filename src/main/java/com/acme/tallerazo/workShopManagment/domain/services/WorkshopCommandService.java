package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopScheduleCommand;

import java.util.Optional;

public interface WorkshopCommandService {
    Optional<Workshop>handle(CreateWorkshopCommand command);
    Optional<Workshop>handle(Long id, UpdateWorkshopCommand command);
    Optional<Workshop>handle(UpdateWorkshopScheduleCommand command);
}
