package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record WorkshopSignUpResource(String workshopName,
                                     String workshopPhone,
                                     String workshopAddress,
                                     String Email,
                                     String photo,
                                     String Description,
                                     List<String> services) {
}
