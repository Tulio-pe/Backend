package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.CreateWorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.CreateWorkshopCommandFromResourceAssembler;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.WorkshopResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "api/v1/workshops", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workshops", description = "Available Workshops EndPoint")
public class WorkshopsController {
    private final  WorkshopQueryService workshopQueryservice;
    private final  WorkshopCommandService workshopcommandService;

     public WorkshopsController(WorkshopQueryService workshopQueryservice, WorkshopCommandService workshopcommandService){
         this.workshopQueryservice=workshopQueryservice;
         this.workshopcommandService = workshopcommandService;
     }
    @GetMapping("/{workshopName}")
    @Operation(summary = "Get workshop By Name")
    @ApiResponses(value={
            @ApiResponse(responseCode="200", description="Workshop found "),
            @ApiResponse(responseCode = "404", description="Workshop not found")
    })
    public ResponseEntity<WorkshopResource>getWorkshopByWorkshopName(@PathVariable String workshopName){
         var  workshopNameObject= new WorkshopName(workshopName);
         var getWorkshopByNameQuery= new GetWorkshopByNameQuery(workshopNameObject);
         var Workshop= workshopQueryservice.handle(getWorkshopByNameQuery);
         if(Workshop.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         var workshopEntity=Workshop.get();
         var workshopResource=
         WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshopEntity));
         return ResponseEntity.ok(workshopResource);
     }

    @PostMapping("/")
    @Operation(summary = "Create workshop", description = "Create a workshop with the provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workshop created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<WorkshopResource>createWorkshop(@RequestBody CreateWorkshopResource createWorkshopResource){
        var createWorkshopCommand = CreateWorkshopCommandFromResourceAssembler.toCommandFromResource(createWorkshopResource);
        var workshop=workshopcommandService.handle(createWorkshopCommand);

        if(workshop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var workshopResource= WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshop.get()));
        return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);
    }
}
