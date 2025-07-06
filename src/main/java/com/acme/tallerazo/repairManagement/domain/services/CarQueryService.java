package com.acme.tallerazo.repairManagement.domain.services;

import com.acme.tallerazo.repairManagement.domain.model.Queries.GetAllCarQuery;
import com.acme.tallerazo.repairManagement.domain.model.Queries.GetCarByPlaceQuery;
import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;

import java.util.List;
import java.util.Optional;

public interface CarQueryService {
    List<Car> handle (GetAllCarQuery query);
    Optional<Car>handle(GetCarByPlaceQuery query);
}
