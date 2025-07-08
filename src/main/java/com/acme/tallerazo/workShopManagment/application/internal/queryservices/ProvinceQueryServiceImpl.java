package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllProvincesByRegionIdQuery;
import com.acme.tallerazo.workShopManagment.domain.services.ProvinceQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceQueryServiceImpl implements ProvinceQueryService {
    private final ProvinceRepository provinceRepository;

    public ProvinceQueryServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public List<Province> handle(GetAllProvincesByRegionIdQuery query) {
        return provinceRepository.findAllByRegionId(query.regionId());
    }
}
