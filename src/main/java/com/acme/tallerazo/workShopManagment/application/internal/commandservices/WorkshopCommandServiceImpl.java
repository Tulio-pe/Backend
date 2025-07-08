package com.acme.tallerazo.workShopManagment.application.internal.commandservices;
import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopLocation;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;
import com.acme.tallerazo.workShopManagment.domain.services.DistrictQueryService;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.DistrictRepository;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;

import java.util.List;
import java.util.Optional;

/**
 * <summary>
 * Service implementing business commands for Workshop entities.
 * </summary>
 */
@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {
    private final WorkshopRepository workshopRepository;
    private final ServiceRepository serviceRepository;
    private final DistrictRepository districtRepository;

    /**
     * <summary>
     * Constructs a new instance of WorkshopCommandServiceImpl.
     * </summary>
     * <param name="workshopRepository">
     *   Repository for persisting and querying Workshop entities.
     * </param>
     * <param name="serviceRepository">
     *   Repository for looking up and validating Service objects.
     * </param>
     */
    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository, ServiceRepository serviceRepository, DistrictRepository districtRepository){
        this.workshopRepository=workshopRepository;
        this.serviceRepository=serviceRepository;
        this.districtRepository = districtRepository;
    }
    /**
     * <summary>
     * Processes the CreateWorkshopCommand to create and persist a new Workshop. Search for district and then add to it
     * </summary>
     * <param name="command">
     *   Command object containing all necessary data to create a Workshop.
     * </param>
     * <returns>
     *   An Optional containing the newly created Workshop if successful; otherwise, Optional.empty().
     * </returns>
     * <exception cref="RuntimeException">
     *   Thrown if a Workshop with the same name already exists
     *   or if any referenced Service cannot be found.
     * </exception>
     * <exception cref="RuntimeException">
     *   Thrown if the Id passed doesn't exist
     *   or if any referenced Service cannot be found.
     * </exception>
     */
    @Override
    public Optional<Workshop> handle(CreateWorkshopCommand command) {
        var district = districtRepository.getDistrictById(command.districtId());
        if (district == null) {
            throw new RuntimeException("District not found");
        }

        WorkshopName workshopName = new WorkshopName(command.workshopName());
        if (workshopRepository.existsByWorkshopName(workshopName)) {
            throw new RuntimeException("Workshop with name '%s' already exists".formatted(command.workshopName()));
        }

        WorkshopLocation location = new WorkshopLocation(command.workshopAddress(), district);

        Workshop workshop = new Workshop(
                command.workshopName(),
                command.workshopPhone(),
                location,
                command.workshopEmail(),
                command.photo(),
                command.workshopDescription(),
                command.managerId(),
                command.services(),
                command.schedule()
        );
        workshopRepository.save(workshop);
        return workshopRepository.findByWorkshopName(workshopName);
    }

    @Override
    public Optional<Workshop> handle(Long id, UpdateWorkshopCommand command){

        Workshop existedWorkshop = workshopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workshop with id '%d' not found".formatted(id)));

        var district = districtRepository.getDistrictById(command.districtId());
        if (district == null) {
            throw new RuntimeException("District not found");
        }

        WorkshopName workshopName = new WorkshopName(command.workshopName());
        if (workshopRepository.existsByWorkshopName(workshopName)) {
            throw new RuntimeException("Workshop with name '%s' already exists".formatted(command.workshopName()));
        }
        WorkshopLocation location = new WorkshopLocation(command.workshopAddress(), district);
        existedWorkshop.update(
                command.workshopName(),
                command.workshopPhone(),
                location,
                command.workshopEmail(),
                command.photo(),
                command.workshopDescription(),
                command.managerId(),
                command.services()
        );
        workshopRepository.save(existedWorkshop);
        return workshopRepository.findByWorkshopName(workshopName);
    }
}
