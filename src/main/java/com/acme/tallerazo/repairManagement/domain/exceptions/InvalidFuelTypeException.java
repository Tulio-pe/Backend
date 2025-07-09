package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the provided fuel type is invalid or blank.
 *
 * <p>Typically used when the fuel type string is null, empty, or does not match allowed values.</p>
 */
public class InvalidFuelTypeException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidFuelTypeException} with the specified invalid fuel type.
     *
     * @param fuelType the invalid fuel type value
     */
    public InvalidFuelTypeException(String fuelType) {
        super(String.format("Invalid fuel type: %s", fuelType));
    }
}
