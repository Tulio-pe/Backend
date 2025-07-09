package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the car ID is null.
 *
 * <p>This exception is used when a required car ID is missing or not initialized.</p>
 */
public class CarIdNullException extends RuntimeException {

    /**
     * Constructs a new {@code CarIdNullException} with the specified null ID.
     *
     * @param id the null car ID (usually null)
     */
    public CarIdNullException(Long id) {
        super(String.format("Car ID %s is null", id));
    }
}
