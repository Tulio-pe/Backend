package com.acme.tallerazo.workShopManagment.domain.model.aggregates;

import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class Workshop extends AuditableAbstractAggregateRoot<Workshop> {
    @Embedded
    @AttributeOverride(name = "workshopName", column = @Column(name = "workshop_name", unique = true))
        private WorkshopName workshopName;
    @Embedded
        private WorkshopPhone workshopPhone;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address",column = @Column(name="workshop_address",unique=true)),}
    )
    private WorkshopAddress workshopAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value",column = @Column(name="workshop_email",unique=true)),}
    )
    private WorkshopEmail workshopEmail;
    @Embedded
    @AttributeOverrides({@AttributeOverride(name="photo",column = @Column(name="workshop_photo"))})
    private Photo photo;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="description", column = @Column(name="workshop_description"))
    })
    private WorkshopDescription description;
    @Embedded
    @AttributeOverride(
            name  = "managerId",
            column = @Column(name = "manager_id", nullable = false)
    )
    private ManagerId managerId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name="workshop_services",joinColumns =@JoinColumn(name="workshop_id"),
            inverseJoinColumns = @JoinColumn(name="services_id"))
    private Set<Service>services;

    public String getWorkshopName(){
        return workshopName.workshopName();
    }

    public String getWorkshopAddress(){return workshopAddress.address();}
    public String getWorkshopPhone(){return workshopPhone.workshopPhone();}
    public String getPhoto(){return photo.photo();}
    public String getDescription(){return description.description();}

    public Workshop(){this.services = new HashSet<>();}

    public Workshop(String workshopName, String workshopPhone, String workshopAddress, String workshopEmail,String photo, String workshopDescription, Long managerId) {
        this.workshopName = new WorkshopName(workshopName);
        this.workshopPhone = new WorkshopPhone(workshopPhone);
        this.workshopAddress=new WorkshopAddress(workshopAddress);
        this.workshopEmail=new WorkshopEmail(workshopEmail);
        this.photo=new Photo(photo);
        this.description=new WorkshopDescription(workshopDescription);
        this.services=new HashSet<>();
        this.managerId=new ManagerId(managerId);
    }
    public Workshop(String workshopName, String workshopPhone, String workshopAddress, String workshopEmail, String photo, String workshopDescription, Long managerId, List<Service>services){
        this(workshopName,workshopPhone,workshopAddress,workshopEmail,photo,workshopDescription,managerId);
        addServices(services);
    }

    public Workshop addServices(List<Service> services){
        var validateServiceSet= Service.ValidateServiceSet(services);
        this.services.addAll(validateServiceSet);
        return this;
    }

}
