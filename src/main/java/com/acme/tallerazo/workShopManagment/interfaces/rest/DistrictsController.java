package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllDistrictsByProvinceIdQuery; // Importa la query
import com.acme.tallerazo.workShopManagment.domain.services.DistrictQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.DistrictResource; // Asumiendo que tienes este DTO
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.DistrictResourceFromEntityAssembler; // Asumiendo que tienes este assembler/mapper

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/districts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Districts", description = "Available district EndPoint")
public class DistrictsController {

    private final DistrictQueryService districtQueryService;

    public DistrictsController(DistrictQueryService districtQueryService) {
        this.districtQueryService = districtQueryService;
    }

    @GetMapping("/by-province/{provinceId}")
    @Operation(summary = "Get districts by province ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Districts found"),
            @ApiResponse(responseCode = "404", description = "No districts found for the given province ID")
    })
    public ResponseEntity<List<DistrictResource>> getDistrictsByProvinceId(@PathVariable Long provinceId) {
        var query = new GetAllDistrictsByProvinceIdQuery(provinceId);

        var districts = districtQueryService.handle(query);

        var districtResources = districts.stream()
                .map(DistrictResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());


        if (districtResources.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(districtResources);
    }

}