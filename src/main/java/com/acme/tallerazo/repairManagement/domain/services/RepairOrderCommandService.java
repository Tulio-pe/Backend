package com.acme.tallerazo.repairManagement.domain.services;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.RepairOrder;
import com.acme.tallerazo.repairManagement.domain.model.commands.CreateRepairCommand;

import java.util.Optional;

public interface RepairOrderCommandService {
    Optional<RepairOrder>handle(CreateRepairCommand command);
}
