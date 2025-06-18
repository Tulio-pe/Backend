package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.WorkshopAndService;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceRepository extends JpaRepository<WorkshopAndService,Long> {
    Optional<WorkshopAndService> findByName(WorkServices workServices);
   boolean existsByName(WorkServices workServices);
}