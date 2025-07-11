package com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.WorkshopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    boolean existsByPlate(Plate plate);
    Optional<Car>findByPlate(Plate plate);
    boolean existsByPlateAndIdNot(Plate plate, Long id);
    List<Car> findCarsByWorkshopId(WorkshopId workshopId);
}
