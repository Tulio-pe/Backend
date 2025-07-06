package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record CreateWorkshopResource(String workshopName,
                                     String workshopPhone,
                                     String workshopAddress,
                                     String email,
                                     String photo,
                                     String description,
                                     Long managerId,
                                     List<String> services) {
}
