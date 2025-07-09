package com.acme.tallerazo.repairManagement.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.RepairOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepairOrderRepository extends JpaRepository<RepairOrder, Long> {
    Optional<RepairOrder>findByCarId(Long carId);

}
