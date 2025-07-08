package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.ProvinceResource;

public class ProvinceResourceFromEntityAssembler {
    public static ProvinceResource toResourceFromEntity(Province province) {
        return new ProvinceResource(province.getId(), province.getName());
    }
}
