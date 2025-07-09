package com.acme.tallerazo.workShopManagment.application.internal.commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateRegionCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.services.RegionCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionCommandServiceImpl implements RegionCommandService {
    private final RegionRepository regionCommandService;
    public RegionCommandServiceImpl(RegionRepository regionCommandService){
        this.regionCommandService = regionCommandService;
    }

    @Override
    public Optional<Region> handle(CreateRegionCommand command) {
        Region region = new Region(command.name());
        Region savedRegion = regionCommandService.save(region);
        return Optional.of(savedRegion);
    }
}
