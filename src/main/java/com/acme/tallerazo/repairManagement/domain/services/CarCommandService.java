package com.acme.tallerazo.repairManagement.domain.services;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;

import java.util.Optional;

public interface CarCommandService {
    Optional<Car> handle(CreateCarCommand command);
}
