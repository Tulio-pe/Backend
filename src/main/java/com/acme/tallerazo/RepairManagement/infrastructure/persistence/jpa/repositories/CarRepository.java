package com.acme.tallerazo.RepairManagement.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.RepairManagement.domain.model.valueobjects.Plate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    boolean existsByPlate(Plate plate);
    Optional<Car>findByPlate(Plate plate);

}
