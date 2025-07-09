package com.acme.tallerazo.workShopManagment.domain.model.aggregates;

import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.tallerazo.workShopManagment.domain.model.commands.CreateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.commands.UpdateWorkshopCommand;
import com.acme.tallerazo.workShopManagment.domain.model.entities.ScheduleEntry;
import com.acme.tallerazo.workShopManagment.domain.model.entities.Service;
import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
    private WorkshopLocation workshopLocation;
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
    private List<Service>services;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "workshop_id", nullable = false)
    private List<ScheduleEntry> schedule = new ArrayList<>();


    public Workshop(){this.services =  new ArrayList<>();}

    public Workshop( String workshopName,
                     String workshopPhone,
                     WorkshopLocation workshopLocation,
                     String workshopEmail,
                     String photo,
                     String workshopDescription,
                     Long managerId,
                     List<Service> services,
                     List<ScheduleEntry> schedule) {
        this.workshopName = new WorkshopName(workshopName);
        this.workshopPhone = new WorkshopPhone(workshopPhone);
        this.workshopLocation = workshopLocation;
        this.workshopEmail = new WorkshopEmail(workshopEmail);
        this.photo = new Photo(photo);
        this.description = new WorkshopDescription(workshopDescription);
        this.managerId = new ManagerId(managerId);
        this.schedule = schedule != null ? new ArrayList<>(schedule) : new ArrayList<>();
        this.services = services != null ? new ArrayList<>(services) : new ArrayList<>();
    }

    public void update(
                    String workshopName,
                    String workshopPhone,
                    WorkshopLocation workshopLocation,
                    String workshopEmail,
                    String photo,
                    String workshopDescription,
                    Long managerId,
                    List<Service> services) {
        this.workshopName = new WorkshopName(workshopName);
        this.workshopPhone = new WorkshopPhone(workshopPhone);
        this.workshopLocation = workshopLocation;
        this.workshopEmail = new WorkshopEmail(workshopEmail);
        this.photo = new Photo(photo);
        this.description = new WorkshopDescription(workshopDescription);
        this.managerId = new ManagerId(managerId);
        this.services = services != null ? new ArrayList<>(services) : new ArrayList<>();
    }

    public void updateSchedule(List<ScheduleEntry> newSchedule) {
        this.schedule.clear();
        if (newSchedule != null) {
            this.schedule.addAll(newSchedule);
        }
    }



    //update workshop info

    /**
     * <summary>
     * Retrieves the workshop’s name.
     * </summary>
     * <returns>
     *   The name of the workshop.
     * </returns>
     */
    public String getWorkshopName() {
        return workshopName.workshopName();
    }

    /**
     * <summary>
     * Retrieves the workshop’s address.
     * </summary>
     * <returns>
     *   The address of the workshop.
     * </returns>
     */
    public String getWorkshopAddress() {
        return workshopLocation.getAddress();
    }

    /**
     * <summary>
     * Retrieves the workshop’s phone number.
     * </summary>
     * <returns>
     *   The phone number of the workshop.
     * </returns>
     */
    public String getWorkshopPhone() {
        return workshopPhone.workshopPhone();
    }

    /**
     * <summary>
     * Retrieves the workshop’s photo URL or identifier.
     * </summary>
     * <returns>
     *   The photo reference of the workshop.
     * </returns>
     */
    public String getPhoto() {
        return photo.photo();
    }

    /**
     * <summary>
     * Retrieves the workshop’s description.
     * </summary>
     * <returns>
     *   A textual description of the workshop.
     * </returns>
     */
    public String getDescription() {
        return description.description();
    }
}
