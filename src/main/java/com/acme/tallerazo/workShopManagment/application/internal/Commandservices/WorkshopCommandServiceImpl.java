package com.acme.tallerazo.workShopManagment.application.internal.Commandservices;
import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopName;
import com.acme.tallerazo.workShopManagment.domain.services.WorkshopCommandService;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.ServiceRepository;
import com.acme.tallerazo.workShopManagment.infrastructure.persistence.jpa.repositories.WorkshopRepository;
import org.springframework.stereotype.Service;
import  com.acme.tallerazo.workShopManagment.domain.model.commands.SignUpCommand;
import java.util.Optional;

@Service
public class WorkshopCommandServiceImpl implements WorkshopCommandService {
    private final WorkshopRepository workshopRepository;
    private final ServiceRepository serviceRepository;
    public WorkshopCommandServiceImpl(WorkshopRepository workshopRepository, ServiceRepository serviceRepository){
        this.workshopRepository=workshopRepository;
        this.serviceRepository=serviceRepository;
    }
    @Override
    public Optional<Workshop> handle(SignUpCommand command) {
         var workshopName=new WorkshopName(command.workshopName());
        if (workshopRepository.existsByWorkshopName(workshopName))
        { throw new RuntimeException("workshop with name %s already exists".formatted(command.workshopName())); }
        var services = command.services().stream().map(role -> serviceRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("service name not found"))).toList();
        var workshop = new Workshop(command.workshopName(), command.workshopPhone(),command.workshopAddress(),command.Email(),command.photo(),command.Description(),services);

        workshopRepository.save(workshop);
        return workshopRepository.findByWorkshopName(workshopName);
    }

}
