package com.acme.tallerazo.repairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.repairManagement.domain.model.commands.CreateRepairCommand;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CreateRepairOrderResource;

public class CreateRepairOrderCommandFromResourceAssembler {
    public static CreateRepairCommand toCommandFromResource(CreateRepairOrderResource resource) {
        return new CreateRepairCommand(resource.plate(), resource.details());
    }
}
