package com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DistrictRepository  extends JpaRepository<District, Long> {
    List<District> findAllByProvinceId(Long id);
    District getDistrictById(Long id);
}
