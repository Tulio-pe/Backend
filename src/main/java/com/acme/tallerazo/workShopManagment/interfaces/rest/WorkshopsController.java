package com.acme.tallerazo.workShopManagment.interfaces.rest;

import com.acme.tallerazo.shared.infrastructure.services.CloudinaryService;
import com.acme.tallerazo.workShopManagment.domain.model.queries.GetWorkshopByNameQuery;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopCommandService;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopQueryService;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.CreateWorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.UpdateWorkshopScheduleResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.resource.WorkshopResource;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.CreateWorkshopCommandFromResourceAssembler;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.UpdateWorkshopScheduleCommandFromResourceAssembler;
import com.acme.tallerazo.workShopManagment.interfaces.rest.transform.WorkshopResourceFromEntityAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/workshops", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@Tag(name = "Workshops", description = "Available Workshops EndPoint")
public class WorkshopsController {

    private static final Logger logger = LoggerFactory.getLogger(WorkshopsController.class);

    private final WorkshopQueryService workshopQueryservice;
    private final WorkshopCommandService workshopcommandService;
    private final CloudinaryService cloudinaryService;
    private final ObjectMapper objectMapper;

    public WorkshopsController(WorkshopQueryService workshopQueryservice,
                               WorkshopCommandService workshopcommandService,
                               CloudinaryService cloudinaryService,
                               ObjectMapper objectMapper) {
        this.workshopQueryservice = workshopQueryservice;
        this.workshopcommandService = workshopcommandService;
        this.cloudinaryService = cloudinaryService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{workshopName}")
    @Operation(summary = "Get workshop By Name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workshop found "),
            @ApiResponse(responseCode = "404", description = "Workshop not found")
    })
    public ResponseEntity<WorkshopResource> getWorkshopByWorkshopName(@PathVariable String workshopName) {
        logger.info("🔍 GET /api/v1/workshops/{}", workshopName);

        try {
            var workshopNameObject = new WorkshopName(workshopName);
            var getWorkshopByNameQuery = new GetWorkshopByNameQuery(workshopNameObject);
            var Workshop = workshopQueryservice.handle(getWorkshopByNameQuery);

            if (Workshop.isEmpty()) {
                logger.info("❌ Workshop not found: {}", workshopName);
                return ResponseEntity.notFound().build();
            }

            var workshopEntity = Workshop.get();
            var workshopResource = WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshopEntity));
            logger.info("✅ Workshop found: {}", workshopResource.workshopName());
            return ResponseEntity.ok(workshopResource);

        } catch (Exception e) {
            logger.error("💥 Error in getWorkshopByWorkshopName: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // 🔥 ENDPOINT PRINCIPAL PARA MULTIPART/FORM-DATA (CON IMAGEN)
    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create workshop with image", description = "Create a workshop with image upload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workshop created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<WorkshopResource> createWorkshopWithImage(
            @RequestPart("workshop") String workshopJson,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        logger.info("🚀 POST /api/v1/workshops/ (multipart/form-data)");
        logger.info("📦 Workshop JSON: {}", workshopJson);
        logger.info("🖼️ Image: {}", image != null ? image.getOriginalFilename() : "No image");

        try {
            // Parse JSON to CreateWorkshopResource
            CreateWorkshopResource createWorkshopResource = objectMapper.readValue(workshopJson, CreateWorkshopResource.class);
            logger.info("✅ Workshop parsed successfully: {}", createWorkshopResource.workshopName());

            // Upload image to Cloudinary if provided
            String imageUrl = createWorkshopResource.photo();
            if (image != null && !image.isEmpty()) {
                logger.info("📸 Uploading image to Cloudinary...");
                imageUrl = cloudinaryService.uploadImage(image);
                logger.info("✅ Image uploaded: {}", imageUrl);
            }

            // Create resource with image URL
            CreateWorkshopResource resourceWithImage = new CreateWorkshopResource(
                    createWorkshopResource.workshopName(),
                    createWorkshopResource.workshopPhone(),
                    createWorkshopResource.workshopAddress(),
                    createWorkshopResource.workshopEmail(),
                    imageUrl,
                    createWorkshopResource.description(),
                    createWorkshopResource.districtId(),
                    createWorkshopResource.services(),
                    createWorkshopResource.schedule()
            );

            logger.info("🔧 Creating workshop command...");
            var createWorkshopCommand = CreateWorkshopCommandFromResourceAssembler.toCommandFromResource(resourceWithImage);
            var workshop = workshopcommandService.handle(createWorkshopCommand);

            if (workshop.isEmpty()) {
                logger.error("❌ Failed to create workshop - command returned empty");
                return ResponseEntity.badRequest().build();
            }

            var workshopResource = WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshop.get()));
            logger.info("✅ Workshop created successfully with ID: {}", workshopResource.workshopName());
            return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);

        } catch (IOException e) {
            logger.error("💥 IOException parsing JSON: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            logger.error("💥 Exception creating workshop: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    // 🔥 ENDPOINT ALTERNATIVO PARA JSON (SIN IMAGEN)
    @PostMapping(value = "/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create workshop (JSON only)", description = "Create a workshop without image")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Workshop created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ResponseEntity<WorkshopResource> createWorkshopJson(@RequestBody CreateWorkshopResource createWorkshopResource) {
        logger.info("🚀 POST /api/v1/workshops/json (application/json)");
        logger.info("📦 Workshop: {}", createWorkshopResource.workshopName());

        try {
            var createWorkshopCommand = CreateWorkshopCommandFromResourceAssembler.toCommandFromResource(createWorkshopResource);
            var workshop = workshopcommandService.handle(createWorkshopCommand);

            if (workshop.isEmpty()) {
                logger.error("❌ Failed to create workshop");
                return ResponseEntity.badRequest().build();
            }

            var workshopResource = WorkshopResourceFromEntityAssembler.ToResourceFromEntity((workshop.get()));
            logger.info("✅ Workshop created successfully: {}", workshopResource.workshopName());
            return new ResponseEntity<>(workshopResource, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("💥 Error creating workshop: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }

    // 🔥 ENDPOINT SIMPLE PARA TESTING
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        logger.info("🧪 GET /api/v1/workshops/test");
        return ResponseEntity.ok("Workshop endpoint is working! ✅");
    }

    @PutMapping("/{id}/schedule")
    @Operation(summary = "Update workshop schedule", description = "Update the schedule of an existing workshop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Workshop schedule updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Workshop not found")
    })
    public ResponseEntity<WorkshopResource> updateWorkshopSchedule(
            @RequestBody UpdateWorkshopScheduleResource updateWorkshopScheduleResource,
            @PathVariable Long id) {

        logger.info("🔄 PUT /api/v1/workshops/{}/schedule", id);

        try {
            var updateWorkshopScheduleCommand =
                    UpdateWorkshopScheduleCommandFromResourceAssembler.toCommandFromResource(id, updateWorkshopScheduleResource);

            var workshop = workshopcommandService.handle(updateWorkshopScheduleCommand);

            if (workshop.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var workshopResource = WorkshopResourceFromEntityAssembler.ToResourceFromEntity(workshop.get());
            return ResponseEntity.ok(workshopResource);

        } catch (Exception e) {
            logger.error("💥 Error updating schedule: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().build();
        }
    }
}
