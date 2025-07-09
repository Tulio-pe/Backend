package com.acme.tallerazo.repairManagement.domain.model.aggregates;

import com.acme.tallerazo.repairManagement.domain.model.commands.CreateCarCommand;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.*;
import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

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
     * Updates the current car's information with new values.
     *
     * @param brand the new brand of the car. Must not be null or blank.
     * @param model the new model of the car. Must not be null or blank.
     * @param plate the new license plate. Must not be null or blank.
     * @param fuelType the new fuel type. Must not be null or blank.
     * @param year the new manufacturing year. Must not be null or blank.
     * @return the updated car instance (this)
     */
    public Car updateInformation(String brand, String model, String plate, String fuelType, String year) {
        this.brand = new Brand(brand);
        this.model = new Model(model);
        this.plate = new Plate(plate);
        this.fuelType = new FuelType(fuelType);
        this.year = new Year(year);
        return this;
    }

    public String getFuelType(){return this.fuelType.fuelType();}

    public String getBrand(){return this.brand.brand();}
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
