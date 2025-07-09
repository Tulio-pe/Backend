package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByName(WorkshopServices workshopServices);
   boolean existsByName(WorkshopServices workshopServices);
}