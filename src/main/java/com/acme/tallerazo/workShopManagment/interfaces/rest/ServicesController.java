package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetAllServicesQuery;
import com.acme.tallerazo.workShopManagment.domain.services.ServiceQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.ServiceResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.ServiceResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/services",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Services",description = "Available service EndPoint")
public class ServicesController {
    private final ServiceQueryService serviceQueryService;
    public ServicesController(ServiceQueryService serviceQueryService) {
        this.serviceQueryService = serviceQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all services")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Services found"),
            @ApiResponse(responseCode = "404", description = "No services found")
    })
    public ResponseEntity<List<ServiceResource>> getAllServices() {
        var query = new GetAllServicesQuery();
        var services = serviceQueryService.handle(query); // Asume que tienes este método o query

        var serviceResources = services.stream()
                .map(ServiceResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        if (serviceResources.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(serviceResources);
    }

}
