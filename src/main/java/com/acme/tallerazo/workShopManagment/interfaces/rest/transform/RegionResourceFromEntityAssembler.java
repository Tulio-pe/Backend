package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.RegionResource;

public class RegionResourceFromEntityAssembler {
    public static RegionResource toResourceFromEntity(Region region) {
        return new RegionResource(region.getId(),region.getRegionName());
    }
}
