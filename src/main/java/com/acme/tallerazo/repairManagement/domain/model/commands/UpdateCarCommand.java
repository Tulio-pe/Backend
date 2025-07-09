package com.acme.tallerazo.repairManagement.domain.model.commands;

import com.acme.tallerazo.repairManagement.domain.exceptions.*;

/**
 * Command to update a car.
 *
 * @param CarId the car ID.
 *              Must not be null or less than 1
 * @param brand the car brand.
 *              Must not be null or blank
 * @param model the car model.
 *              Must not be null or blank
 * @param plate the car license plate.
 *              Must not be null or blank
 * @param fuelType the car fuel type.
 *                 Must not be null or blank
 * @param year the car manufacturing year.
 *             Must not be null or blank
 *
 * @throws CarIdNullException if {@code CarId} is null or less than 1
 * @throws InvalidBrandException if {@code brand} is null or blank
 * @throws BlankModelException if {@code model} is null or blank
 * @throws InvalidPlateException if {@code plate} is null or blank
 * @throws InvalidFuelTypeException if {@code fuelType} is null or blank
 * @throws InvalidYearException if {@code year} is null or blank
 */
public record UpdateCarCommand(Long CarId, String brand, String model, String plate, String fuelType, String year) {

    /**
     * Constructor with validation logic.
     *
     * @param CarId the car ID
     * @param brand the car brand
     * @param model the car model
     * @param plate the car license plate
     * @param fuelType the car fuel type
     * @param year the car manufacturing year
     *
     * @throws CarIdNullException if {@code CarId} is null or less than 1
     * @throws InvalidBrandException if {@code brand} is null or blank
     * @throws BlankModelException if {@code model} is null or blank
     * @throws InvalidPlateException if {@code plate} is null or blank
     * @throws InvalidFuelTypeException if {@code fuelType} is null or blank
     * @throws InvalidYearException if {@code year} is null or blank
     */
    public UpdateCarCommand {
        if (CarId == null || CarId < 1) {
            throw new CarIdNullException(CarId);
        }
        if (brand == null || brand.isBlank()) {
            throw new InvalidBrandException(brand);
        }
        if (model == null || model.isBlank()) {
            throw new BlankModelException(model);
        }
        if (plate == null || plate.isBlank()) {
            throw new InvalidPlateException(plate);
        }
        if (fuelType == null || fuelType.isBlank()) {
            throw new InvalidFuelTypeException(fuelType);
        }
        if (year == null || year.isBlank()) {
            throw new InvalidYearException(year);
        }
    }
}
