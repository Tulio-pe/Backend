package com.acme.tallerazo.workShopManagment.application.internal.eventhandlers;

import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateDistrictCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateProvinceCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateRegionCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.SeedServicesCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Province;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Region;
import com.acme.tallerazo.workShopManagment.domain.services.DistrictCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.ProvinceCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.RegionCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.external.api.ExternalGeoApiClient;
import com.acme.tallerazo.workShopManagment.infrastructure.external.api.resources.ExternalGeoResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class ApplicationReadyEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    private final ServiceCommandService serviceCommandService;
    private final ExternalGeoApiClient externalGeoApiClient;
    private final RegionCommandService regionCommandService;
    private final ProvinceCommandService provinceCommandService;
    private final DistrictCommandService districtCommandService;

    public ApplicationReadyEventHandler(ServiceCommandService serviceCommandService,
                                        ExternalGeoApiClient externalGeoApiClient,
                                        RegionCommandService regionCommandService,
                                        ProvinceCommandService provinceCommandService,
                                        DistrictCommandService districtCommandService) {
        this.serviceCommandService = serviceCommandService;
        this.externalGeoApiClient = externalGeoApiClient;
        this.regionCommandService = regionCommandService;
        this.provinceCommandService = provinceCommandService;
        this.districtCommandService = districtCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        String applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting core services seeding for {} at {}",
                applicationName, currentTimestamp());
        serviceCommandService.handle(new SeedServicesCommand());
        LOGGER.info("Core services seeding finished for {} at {}",
                applicationName, currentTimestamp());

        LOGGER.info("Starting locations seeding for {} at {}",
                applicationName, currentTimestamp());

        List<ExternalGeoResource> locations = externalGeoApiClient.fetchDistricts();

        // 1) Seed unique regions
        Map<String, Region> regionByName = new HashMap<>();
        for (ExternalGeoResource loc : locations) {
            String regionName = loc.NOMBDEP();
            if (!regionByName.containsKey(regionName)) {
                Region region = regionCommandService
                        .handle(new CreateRegionCommand(regionName))
                        .orElseThrow(() -> new IllegalStateException("Failed to seed region " + regionName));
                regionByName.put(regionName, region);
            }
        }

        // 2) Seed unique provinces
        Map<String, Province> provinceByName = new HashMap<>();
        for (ExternalGeoResource loc : locations) {
            String provinceName = loc.NOMBPROV();
            String regionName = loc.NOMBDEP();
            if (!provinceByName.containsKey(provinceName)) {
                Region region = regionByName.get(regionName);
                Province province = provinceCommandService
                        .handle(new CreateProvinceCommand(provinceName, region.getId()))
                        .orElseThrow(() -> new IllegalStateException("Failed to seed province " + provinceName));
                provinceByName.put(provinceName, province);
            }
        }

        // 3) Seed unique districts
        Set<String> seenDistricts = new HashSet<>();
        for (ExternalGeoResource loc : locations) {
            String districtName = loc.NOMBDIST();
            String provinceName = loc.NOMBPROV();
            if (!seenDistricts.add(districtName + "|" + provinceName)) continue; // evita repetidos

            Province parent = provinceByName.get(provinceName);
            if (parent == null) {
                LOGGER.warn("Skipping district {} because province {} not seeded", districtName, provinceName);
                continue;
            }
            districtCommandService.handle(new CreateDistrictCommand(districtName, parent.getId()));
        }


        LOGGER.info("Locations seeding finished for {} at {}",
                applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
