package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
