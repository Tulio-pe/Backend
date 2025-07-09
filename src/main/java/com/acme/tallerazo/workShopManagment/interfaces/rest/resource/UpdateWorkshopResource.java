package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record UpdateWorkshopResource(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String workshopEmail,
        String photo,
        String workshopDescription,
        Long managerId,
        Long districtId,
        List<String> services
) {
}
