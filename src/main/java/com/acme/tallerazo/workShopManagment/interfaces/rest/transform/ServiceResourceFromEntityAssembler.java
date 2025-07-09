package com.acme.tallerazo.workShopManagment.interfaces.rest.transform;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.ServiceResource;

public class ServiceResourceFromEntityAssembler {
public static ServiceResource toResourceFromEntity(Service service){
    return new ServiceResource(service.getStringName());
}
}
