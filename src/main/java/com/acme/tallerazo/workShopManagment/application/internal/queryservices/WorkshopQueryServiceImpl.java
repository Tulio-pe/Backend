package com.acme.tallerazo.workShopManagment.application.internal.queryservices;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllWorkshopsByDistrictIdQuery;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopQueryService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopQueryServiceImpl implements WorkshopQueryService {
    private final WorkshopRepository workshopRepository;
 public WorkshopQueryServiceImpl(WorkshopRepository workshopRepository) {
     this.workshopRepository=workshopRepository;
 }
    @Override
    public Optional<Workshop> handle(GetWorkshopByNameQuery query) {
       return workshopRepository.findByWorkshopName(query.workshopName());
    }

    @Override
    public List<Workshop> handle(GetAllWorkshopsByDistrictIdQuery query) {
     return workshopRepository.findAllByWorkshopLocationDistrictId(query.districId());
    }
}
