package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetDistrictByIdQuery;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllDistrictsByProvinceIdQuery;
import com.acme.tallerazo.workShopManagment.domain.services.DistrictQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.DistrictRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DistrictQueryServiceImpl implements DistrictQueryService {

    private final DistrictRepository districtRepository;

    public DistrictQueryServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<District> handle(GetAllDistrictsByProvinceIdQuery query)
    {
        return districtRepository.findAllByProvinceId(query.provinceId());
    }
    @Override
    public Optional<District>handle(GetDistrictByIdQuery query) {
      return  districtRepository.findById(query.districtId());

    }
}
