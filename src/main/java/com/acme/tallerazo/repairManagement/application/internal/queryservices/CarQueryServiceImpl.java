package com.acme.tallerazo.repairManagement.application.internal.queryservices;

import com.acme.tallerazo.repairManagement.domain.exceptions.PlateNotFoundException;
import com.acme.tallerazo.repairManagement.domain.model.queries.GetAllCarsQuery;
import com.acme.tallerazo.repairManagement.domain.model.queries.GetCarByPlaceQuery;
import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.queries.GetCarsByCompanyIdQuery;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.WorkshopId;
import com.acme.tallerazo.repairManagement.domain.services.CarQueryService;
import com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarQueryServiceImpl implements CarQueryService {
    private final CarRepository carRepository;
    public CarQueryServiceImpl(CarRepository carRepository){
        this.carRepository=carRepository;
    }

    @Override
    public List<Car> handle(GetAllCarsQuery query) {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> handle(GetCarByPlaceQuery query) {
        if(!carRepository.existsByPlate(query.plate())){throw new PlateNotFoundException(query.plate().plate());
        }
        return carRepository.findByPlate(query.plate());
    }
    @Override
    public List<Car> handle(GetCarsByCompanyIdQuery query) {
        return carRepository.findCarsByWorkshopId(new WorkshopId(query.companyId()));
    }
}
