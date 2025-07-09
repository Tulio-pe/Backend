package com.acme.tallerazo.workShopManagment.application.internal.commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateProvinceCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.services.ProvinceCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.RegionCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceCommandServiceImpl implements ProvinceCommandService {
    private final ProvinceRepository provinceRepository;
    private final RegionRepository regionRepository;
    public ProvinceCommandServiceImpl(ProvinceRepository provinceRepository, RegionRepository regionRepository) {
        this.provinceRepository = provinceRepository;
        this.regionRepository = regionRepository;
    }
    @Override
    public Optional<Province> handle(CreateProvinceCommand command) {
        Region region = regionRepository.findById(command.regionId())
                .orElseThrow(() -> new EntityNotFoundException("Región no encontrada"));
        Province province = new Province(command.name(), region);
        Province savedProvince = provinceRepository.save(province);
        return Optional.of(savedProvince);
    }

}
