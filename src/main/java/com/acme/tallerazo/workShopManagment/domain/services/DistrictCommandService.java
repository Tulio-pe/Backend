package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateDistrictCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.District;

import java.util.Optional;

public interface DistrictCommandService {

    Optional<District> handle(CreateDistrictCommand command);
}
