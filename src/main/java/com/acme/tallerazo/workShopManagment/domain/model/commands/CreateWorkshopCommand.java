package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;

import java.util.List;

public record CreateWorkshopCommand(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String Email,
        String photo,
        String Description,
        List<Service> services) {


}
