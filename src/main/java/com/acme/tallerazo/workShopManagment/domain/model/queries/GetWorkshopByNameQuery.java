package com.acme.tallerazo.workShopManagment.domain.model.queries;

import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;

public record GetWorkshopByNameQuery(WorkshopName workshopName) {
}
