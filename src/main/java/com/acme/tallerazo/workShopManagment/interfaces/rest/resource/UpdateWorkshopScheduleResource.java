package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record UpdateWorkshopScheduleResource(
        List<ScheduleEntryResource> schedule
) {
}
