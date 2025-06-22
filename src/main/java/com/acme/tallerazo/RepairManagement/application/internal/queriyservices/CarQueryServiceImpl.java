package com.acme.tallerazo.RepairManagement.application.internal.queriyservices;

import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetAllCarQuery;
import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetCarByPlaceQuery;
import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.RepairManagement.domain.services.CarQueryService;
import com.acme.tallerazo.RepairManagement.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Delegates read-only car queries to {@link CarRepository}.
 * <p>
 *  Provee dos manejadores:
 *  <ul>
 *    <li>{@link #handle(GetAllCarQuery)} – devuelve todas las unidades registradas.</li>
 *    <li>{@link #handle(GetCarByPlaceQuery)} – busca un auto por placa.</li>
 *  </ul>
 */
@Service
public class CarQueryServiceImpl implements CarQueryService {
    private final CarRepository carRepository;
    public CarQueryServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Override
    public List<Car> handle(GetAllCarQuery query) {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> handle(GetCarByPlaceQuery query) {
        if(!carRepository.existsByPlate(query.plate())){throw new IllegalArgumentException("plate not found");
        }
        return carRepository.findByPlate(query.plate());

    }
}
