package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.WorkshopResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "api/v1/workshops", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workshops", description = "Available Workshops EndPoint")
public class WorkshopsController {

  private final  WorkshopQueryService workshoQueryservice;
 public WorkshopsController(WorkshopQueryService workshoQueryservice){
     this.workshoQueryservice=workshoQueryservice;
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
     var Workshop= workshoQueryservice.handle(getWorkshopByNameQuery);
     if(Workshop.isEmpty()){
         return ResponseEntity.notFound().build();
     }
     var workshopEntity=Workshop.get();
     var workshopResource=
     WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshopEntity));
     return ResponseEntity.ok(workshopResource);

 }


}
