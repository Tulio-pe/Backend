package com.acme.tallerazo.workShopManagment.interfaces.rest.resource;

import java.util.List;

public record WorkshopResource(String WorkshopName, String WorkshopAddress, String WorkshopPhone, String WorkshopPhoto, String WorkshopDescription,
                               List<String> services) {

}
