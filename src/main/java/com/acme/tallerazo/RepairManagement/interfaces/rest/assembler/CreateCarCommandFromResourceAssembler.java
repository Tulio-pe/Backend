package com.acme.tallerazo.RepairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.RepairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.RepairManagement.interfaces.rest.resource.CreateCarResource;

public class CreateCarCommandFromResourceAssembler {
    public static CreateCarCommand toCommandFromResource(CreateCarResource resource){
        return new CreateCarCommand(
                resource.brand(),
                resource.model(),
                resource.licensePlate(),
                resource.fuelType(),
                resource.Year()
        );
    }
}
