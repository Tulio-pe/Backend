package com.acme.tallerazo.repairManagement.domain.services;

import com.acme.tallerazo.repairManagement.domain.model.queries.GetAllCarsQuery;
import com.acme.tallerazo.repairManagement.domain.model.queries.GetCarByPlaceQuery;
import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;

import java.util.List;
import java.util.Optional;

public interface CarQueryService {
    List<Car> handle (GetAllCarsQuery query);
    Optional<Car>handle(GetCarByPlaceQuery query);
}
