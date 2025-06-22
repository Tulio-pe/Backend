package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;

import java.util.List;

public record SignUpCommand(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String Email,
        String photo,
        String Description,
        List<WorkshopAndService> services) {


}
