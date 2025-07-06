package com.acme.tallerazo.repairManagement.interfaces.rest;

import com.acme.tallerazo.repairManagement.domain.model.Queries.GetAllCarsQuery;
import com.acme.tallerazo.repairManagement.domain.model.Queries.GetCarByPlaceQuery;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.repairManagement.domain.services.CarCommandService;
import com.acme.tallerazo.repairManagement.domain.services.CarQueryService;
import com.acme.tallerazo.repairManagement.interfaces.rest.assembler.CarResourceFromEntityAssembler;
import com.acme.tallerazo.repairManagement.interfaces.rest.assembler.CreateCarCommandFromResourceAssembler;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CarResource;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CreateCarResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Cars", description = "Available Cars EndPoint ")
public class CarsController {
    private final CarCommandService carCommandService;
    private final CarQueryService carQueryService;

    public CarsController(CarCommandService carCommandService, CarQueryService carQueryService) {
        this.carCommandService = carCommandService;
        this.carQueryService = carQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new Car", description = "Create a new car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create a new car"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })

    public ResponseEntity<CarResource> createCar(CreateCarResource resource) {
        var createCarCommand = CreateCarCommandFromResourceAssembler.toCommandFromResource(resource);
        var car = carCommandService.handle(createCarCommand);
        if (car.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var createcar = car.get();
        var carResource = CarResourceFromEntityAssembler.toResourceFromEntity(createcar);
        return new ResponseEntity<>(carResource, HttpStatus.CREATED);

    }
    @GetMapping("/{plate}")
    @Operation(summary = "Get a car by Plate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found"),
            @ApiResponse(responseCode = "404", description = "Car not found")
    })
    public ResponseEntity<CarResource> getCarByPlate(@PathVariable String plate) {
        var carPlate = new Plate(plate);
        var getCarByPlateQuery = new GetCarByPlaceQuery(carPlate); // corregido "Place" por "Plate"
        var car = carQueryService.handle(getCarByPlateQuery);

        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var carEntity = car.get();
        var carResource = CarResourceFromEntityAssembler.toResourceFromEntity(carEntity);
        return ResponseEntity.ok(carResource);
    }

    @GetMapping
    @Operation(summary = "Get all Car")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Cars found"),
            @ApiResponse(responseCode = "404", description = "Cars not found")
    })
    public ResponseEntity<List<CarResource>>getAllCars(){
        var cars= carQueryService.handle(new GetAllCarsQuery());
        if(cars.isEmpty()){return ResponseEntity.notFound().build();}
        var carResources= cars.stream().map(CarResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(carResources);
    }

}
