package com.acme.tallerazo.repairManagement.domain.services;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.repairManagement.domain.model.commands.UpdateCarCommand;

import java.util.Optional;

public interface CarCommandService {
    Optional<Car> handle(CreateCarCommand command);
    Optional<Car>handle(UpdateCarCommand command);
}
