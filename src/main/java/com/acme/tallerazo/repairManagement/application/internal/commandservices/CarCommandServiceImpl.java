package com.acme.tallerazo.repairManagement.application.internal.commandservices;

import com.acme.tallerazo.repairManagement.domain.exceptions.PlateAlreadyExistsException;
import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.repairManagement.domain.services.CarCommandService;
import com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CarCommandServiceImpl implements CarCommandService {
    private final  CarRepository carRepository;
    public CarCommandServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Override
    public Optional<Car>handle(CreateCarCommand command) {
        var plate=new Plate(command.licensePlate());
        if(carRepository.existsByPlate(plate)){
            throw  new PlateAlreadyExistsException(plate.plate());
        }
        var car= new Car(command);
        carRepository.save(car);
        return  Optional.of(car);
    }
}
