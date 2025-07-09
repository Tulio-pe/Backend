package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllRegionsQuery;
import com.acme.tallerazo.workShopManagment.domain.services.RegionQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.RegionResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.RegionResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/regions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Regions", description = "Available region EndPoint")
public class RegionsController {
    private final RegionQueryService regionQueryService;

    public RegionsController(RegionQueryService regionQueryService) {
        this.regionQueryService = regionQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Regions found"),
            @ApiResponse(responseCode = "404", description = "No regions found")
    })
    public ResponseEntity<List<RegionResource>> getAllRegions() {
        var query = new GetAllRegionsQuery();


        var regions = regionQueryService.handle(query);

        var regionResources = regions.stream()
                .map(RegionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        if (regionResources.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(regionResources);
    }

}