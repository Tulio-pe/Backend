package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record WorkshopResource(String workshopName, String workshopAddress, String workshopPhone, String workshopPhoto, String workshopDescription,
                               List<String> services) {

}
