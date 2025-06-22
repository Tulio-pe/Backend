package com.acme.tallerazo.RepairManagement.interfaces.rest;

import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetAllCarQuery;
import com.acme.tallerazo.RepairManagement.domain.model.Queries.GetCarByPlaceQuery;
import com.acme.tallerazo.RepairManagement.domain.model.valueobjects.Plate;
import com.acme.tallerazo.RepairManagement.domain.services.CarCommandService;
import com.acme.tallerazo.RepairManagement.domain.services.CarQueryService;
import com.acme.tallerazo.RepairManagement.interfaces.rest.assembler.CarResourceFromEntityAssembler;
import com.acme.tallerazo.RepairManagement.interfaces.rest.assembler.CreateCarCommandFromResourceAssembler;
import com.acme.tallerazo.RepairManagement.interfaces.rest.resource.CarResource;
import com.acme.tallerazo.RepairManagement.interfaces.rest.resource.CreateCarResource;
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



}
