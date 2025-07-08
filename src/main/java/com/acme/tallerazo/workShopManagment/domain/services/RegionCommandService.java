package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateRegionCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;

import java.util.Optional;

public interface RegionCommandService {
    Optional<Region> handle(CreateRegionCommand command);
}
