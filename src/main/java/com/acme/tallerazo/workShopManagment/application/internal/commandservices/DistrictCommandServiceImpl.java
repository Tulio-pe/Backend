package com.acme.tallerazo.workShopManagment.application.internal.commandservices;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateDistrictCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.services.DistrictCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.DistrictRepository;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ProvinceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistrictCommandServiceImpl implements DistrictCommandService {

    private final DistrictRepository districtRepository;
    private final ProvinceRepository provinceRepository;

    public DistrictCommandServiceImpl(DistrictRepository districtRepository,
                                      ProvinceRepository provinceRepository) {
        this.districtRepository = districtRepository;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Optional<District> handle(CreateDistrictCommand command) {
        Province province = provinceRepository.findById(command.provinceId())
                .orElseThrow(() -> new EntityNotFoundException("Provincia no encontrada"));
        District district = new District(command.name(), province);
        District savedDistrict = districtRepository.save(district);
        return Optional.of(savedDistrict);
    }
}

