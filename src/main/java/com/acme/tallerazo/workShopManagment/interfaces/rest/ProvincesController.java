package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllProvincesByRegionIdQuery;
import com.acme.tallerazo.workShopManagment.domain.services.ProvinceQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.ProvinceResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.ProvinceResourceFromEntityAssembler;
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
@RequestMapping(name="api/v1/provinces",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Provinces",description = "Available province EndPoint")
public class ProvincesController {
    private final ProvinceQueryService provinceQueryservice;
    public ProvincesController(ProvinceQueryService provinceQueryservice) {
        this.provinceQueryservice = provinceQueryservice;
    }
    @GetMapping("/{regionId}")
    @Operation(summary = "Get provinces By region id")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Workshop found "),
            @ApiResponse(responseCode = "404", description="Workshop not found")
    })
    public ResponseEntity<List<ProvinceResource>>getProvincesByRegionId(@PathVariable Long regionId) {
     var getallProvincesByRegionId= new GetAllProvincesByRegionIdQuery(regionId);
     var provinces= provinceQueryservice.handle(getallProvincesByRegionId);
        var provinceResources = provinces.stream()
                .map(ProvinceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        if (provinceResources.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provinceResources);
    }
}
