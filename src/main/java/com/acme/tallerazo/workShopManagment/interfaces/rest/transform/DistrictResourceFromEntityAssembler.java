package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.DistrictResource;

public class DistrictResourceFromEntityAssembler {
    public static DistrictResource toResourceFromEntity(District district) {
        return new DistrictResource(district.getId(), district.getName());
    }
}
