package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictById;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictsByProvinceId;
import com.acme.tallerazo.workShopManagment.domain.services.DistrictQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.DistrictRepository;

import java.util.List;
import java.util.Optional;

public class DistrictQueryServiceImpl implements DistrictQueryService {

    private final DistrictRepository districtRepository;

    public DistrictQueryServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<District> handle(GetDistrictsByProvinceId query) {
        return List.of();
    }
    @Override
    public Optional<District>handle(GetDistrictById query) {
        var district = districtRepository.getDistrictById(query.districtId());
        return Optional.of(district);
    }
}
