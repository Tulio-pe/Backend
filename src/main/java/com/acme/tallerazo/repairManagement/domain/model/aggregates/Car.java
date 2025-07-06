package com.acme.tallerazo.repairManagement.domain.model.aggregates;

import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.*;
import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Car extends AuditableAbstractAggregateRoot<Car> {
    @Embedded
    @AttributeOverride(name="brand",column = @Column(name="car_brand"))
    private Brand brand;
    @Embedded
    @AttributeOverride(name="model",column = @Column(name="car_model"))
    private Model model;
    @Embedded
    @AttributeOverride(name="plate",column = @Column(name="license_plate"))
    private Plate plate;
    @Embedded
    private FuelType fuelType;
    @Embedded
    @AttributeOverride(name="year",column = @Column(name="year_of_the_chariot"))
    private Year year;
    @Embedded
    @AttributeOverride(
            name  = "workshopId",
            column = @Column(name = "workshop_id", nullable = false)
    )
    private WorkshopId workshopId;

    public Car(){}
    /**
     * Constructs a new {@code Car} instance based on the provided {@link CreateCarCommand}.
     *
     * @param command the command containing the car information to initialize:
     *                brand, model, license plate, fuel type, and manufacturing year.
     */

    public Car(CreateCarCommand command) {
            this.brand= new Brand(command.brand());
            this.model= new Model(command.model());
            this.plate= new Plate(command.licensePlate());
            this.fuelType= new FuelType(command.fuelType());
            this.year= new Year(command.year());
            this.workshopId= new WorkshopId(command.workshopId());
    }
    /**
     * Returns the car's model.
     *
     * @return the model as a {@code String}.
     */
    public String getModel(){return this.model.model();}

    /**
     * Returns the car's license plate.
     *
     * @return the license plate as a {@code String}.
     */
    public String getPlate(){return this.plate.plate();}
    /**
     * Returns the car's manufacturing year.
     *
     * @return the year as a {@code String}.
     */
   public String getYear(){return this.year.year();}

}
