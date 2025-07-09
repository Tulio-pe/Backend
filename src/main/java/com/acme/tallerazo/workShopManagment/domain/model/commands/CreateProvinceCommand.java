package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;

public record CreateProvinceCommand(String name, Long regionId) {
}
