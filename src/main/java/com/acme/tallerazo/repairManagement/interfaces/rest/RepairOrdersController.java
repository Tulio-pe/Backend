package com.acme.tallerazo.repairManagement.interfaces.rest;

import com.acme.tallerazo.repairManagement.domain.services.RepairOrderCommandService;
import com.acme.tallerazo.repairManagement.interfaces.rest.assembler.CreateRepairOrderCommandFromResourceAssembler;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CreateRepairOrderResource;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.RepairOrderResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/repairs", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Repair Orders", description = "Repair Order endpoints")
public class RepairOrdersController {

    private final RepairOrderCommandService repairOrderCommandService;

    public RepairOrdersController(RepairOrderCommandService repairOrderCommandService) {
        this.repairOrderCommandService = repairOrderCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new Repair Order", description = "Registers a new repair order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Repair order created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<RepairOrderResource> createRepairOrder(@RequestBody CreateRepairOrderResource resource) {
        var command = CreateRepairOrderCommandFromResourceAssembler.toCommandFromResource(resource);
        var repairOrder = repairOrderCommandService.handle(command);
        if (repairOrder.isEmpty()) return ResponseEntity.badRequest().build();

        var entity = repairOrder.get();
        var response = new RepairOrderResource(
                entity.getRepairId(),
                entity.getPlate(),
                entity.getStatus(),
                entity.getDetails().details()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
