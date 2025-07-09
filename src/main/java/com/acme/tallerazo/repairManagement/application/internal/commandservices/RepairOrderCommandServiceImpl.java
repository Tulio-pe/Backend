package com.acme.tallerazo.repairManagement.application.internal.commandservices;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.aggregates.RepairOrder;
import com.acme.tallerazo.repairManagement.domain.model.commands.CreateRepairCommand;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.RepairId;
import com.acme.tallerazo.repairManagement.domain.services.RepairOrderCommandService;
import com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories.CarRepository;
import com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories.RepairOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RepairOrderCommandServiceImpl implements RepairOrderCommandService {

    private final RepairOrderRepository repairOrderRepository;
    private final CarRepository carRepository;

    public RepairOrderCommandServiceImpl(RepairOrderRepository repairOrderRepository,
                                         CarRepository carRepository) {
        this.repairOrderRepository = repairOrderRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Optional<RepairOrder> handle(CreateRepairCommand command) {
        var plate = new Plate(command.Plate());
        Optional<Car> carOptional = carRepository.findByPlate(plate);
        if (carOptional.isEmpty()) {
            throw new IllegalArgumentException("Car with plate " + command.Plate() + " not found.");
        }

        Car car = carOptional.get();
        RepairId repairId = new RepairId(UUID.randomUUID().toString());
        RepairOrder repairOrder = new RepairOrder(car, command, repairId);
        repairOrderRepository.save(repairOrder);
        return Optional.of(repairOrder);
    }
}
