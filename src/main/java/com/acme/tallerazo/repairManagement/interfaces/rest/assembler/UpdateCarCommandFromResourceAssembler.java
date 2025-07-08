package com.acme.tallerazo.repairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.repairManagement.domain.model.commands.UpdateCarCommand;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.UpdateCarResource;

public class UpdateCarCommandFromResourceAssembler {
    public static UpdateCarCommand toCommandFromResource(Long carId, UpdateCarResource resource)
    {
        return new UpdateCarCommand(carId,resource.brand(),resource.model(),resource.plate(),resource.fuelType(),resource.year());
    }
}
