package com.acme.tallerazo.RepairManagement.domain.services;

import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetAllCarQuery;
import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetCarByPlaceQuery;
import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;

import java.util.List;
import java.util.Optional;

public interface CarQueryService {
    List<Car> handle (GetAllCarQuery query);
    Optional<Car>handle(GetCarByPlaceQuery query);
}