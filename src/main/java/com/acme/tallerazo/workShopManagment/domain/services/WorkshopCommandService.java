package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;

import java.util.Optional;

public interface WorkshopCommandService {

    Optional<Workshop>handle(CreateWorkshopCommand command) ;

}
