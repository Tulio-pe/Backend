package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;

public record CreateDistrictCommand(String name, Long provinceId) {
}
