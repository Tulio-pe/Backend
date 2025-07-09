package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

public record ScheduleEntryResource(
        String dayOfWeek,
        String startTime,
        String endTime,
        boolean enabled
) {
}
