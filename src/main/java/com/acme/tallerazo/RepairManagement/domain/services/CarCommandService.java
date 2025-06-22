package com.acme.tallerazo.RepairManagement.domain.services;

import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.RepairManagement.domain.model.commands.CreateCarCommand;

import java.util.Optional;

public interface CarCommandService {
    Optional<Car> handle(CreateCarCommand command);
}
