package com.acme.tallerazo.workShopManagment.domain.services;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateProvinceCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;

import java.util.Optional;

public interface ProvinceCommandService {
    Optional<Province> handle(CreateProvinceCommand command);
}
