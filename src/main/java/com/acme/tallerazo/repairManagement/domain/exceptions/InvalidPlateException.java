package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the provided license plate is invalid or blank.
 *
 * <p>Typically used when the plate string is null, empty, or does not match the expected format.</p>
 */
public class InvalidPlateException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidPlateException} with the specified invalid plate.
     *
     * @param plate the invalid license plate value
     */
    public InvalidPlateException(String plate) {
        super(String.format("Invalid plate: %s", plate));
    }
}
