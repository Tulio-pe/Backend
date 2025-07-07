package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
