package com.acme.tallerazo.repairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CreateCarResource;

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
