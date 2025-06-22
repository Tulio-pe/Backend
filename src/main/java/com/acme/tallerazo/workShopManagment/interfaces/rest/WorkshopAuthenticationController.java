package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.workShopManagment.domain.services.WorkshopCommandService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopSignUpResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.WorkshopResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value="api/v1/Authentication-Workshop", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
@Tag(name="WorkshopRegistration", description = "Available endpoints for workshop registration")
public class WorkshopAuthenticationController {
    private final WorkshopCommandService workshopcommandService;
    public WorkshopAuthenticationController(WorkshopCommandService workshopcommandService){
        this.workshopcommandService = workshopcommandService;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign-up", description = "Sign.up with the provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workshop created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
        public ResponseEntity<WorkshopResource>signUp(@RequestBody WorkshopSignUpResource signUpResource){
        var SignUpCommand = SignUpCommandFromResourceAssembler.toCommandFromResource(signUpResource);
             var workshop=workshopcommandService.handle(SignUpCommand);

            if(workshop.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            var workshopResource= WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshop.get()));
            return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);
        }


}
