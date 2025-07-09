package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllRegionsQuery;
import com.acme.tallerazo.workShopManagment.domain.services.RegionQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionQueryServiceImpl implements RegionQueryService {
    private final RegionRepository regionRepository;
    public RegionQueryServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<Region> handle(GetAllRegionsQuery query) {
        return regionRepository.findAll();
    }
}
