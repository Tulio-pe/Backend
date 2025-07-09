package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.ScheduleEntryResource;

import java.util.List;

public record CreateWorkshopResource(
        String workshopName,
        String workshopPhone,
        String workshopAddress,
        String workshopEmail,
        String photo,
        String description,
        Long managerId,
        Long districtId,
        List<String> services,
        List<ScheduleEntryResource> schedule
) {}
