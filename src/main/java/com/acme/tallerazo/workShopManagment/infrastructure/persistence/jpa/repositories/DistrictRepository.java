package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository  extends JpaRepository<District, Long> {
    List<District> getDistrictByProvinceId(Long id);

    District getDistrictById(Long id);
}
