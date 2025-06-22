package com.acme.tallerazo.RepairManagement.application.internal.commandservices;

import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.RepairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.RepairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.RepairManagement.domain.services.CarCommandService;
import com.acme.tallerazo.RepairManagement.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
* Servicio de comando para la gestión de vehículos en el sistema Tallerazo.
* Implementa la lógica de negocio para crear nuevos vehículos siguiendo el patrón CQRS.
* 
* Esta clase se encarga de:
* - Validar que no existan vehículos duplicados por placa
* - Crear nuevos registros de vehículos en el sistema
* - Persistir la información del vehículo en la base de datos
*/

@Service
public class CarCommandServiceImpl implements CarCommandService {
    private CarRepository carRepository;
    public CarCommandServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Override
    public Optional<Car>handle(CreateCarCommand command) {
        var plate=new Plate(command.licensePlate());
        if(carRepository.existsByPlate(plate)){
            throw  new IllegalArgumentException("Plate already exists");
        }
        var car= new Car(command);
        carRepository.save(car);
        return  Optional.of(car);
    }
}
