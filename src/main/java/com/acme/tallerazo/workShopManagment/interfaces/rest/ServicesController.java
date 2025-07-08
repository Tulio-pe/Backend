package com.acme.tallerazo.workShopManagment.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/services",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Services",description = "Available service EndPoint")
public class ServicesController {

}
