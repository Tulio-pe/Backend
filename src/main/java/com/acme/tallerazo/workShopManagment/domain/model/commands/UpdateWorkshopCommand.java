package com.acme.tallerazo.workShopManagment.domain.model.commands;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopLocation;

import java.util.List;

public record UpdateWorkshopCommand(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String workshopEmail,
        String photo,
        String workshopDescription,
        Long districtId,
        Long managerId,
        List<Service> services
) {
}
