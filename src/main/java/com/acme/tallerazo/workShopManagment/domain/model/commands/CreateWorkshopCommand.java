package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;

import java.util.List;

public record CreateWorkshopCommand(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String email,
        String photo,
        String description,
        Long managerId,
        List<Service> services) {
}
