package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the provided brand is invalid or blank.
 *
 * <p>Typically used when the brand string is null, empty, or contains only whitespace.</p>
 */
public class InvalidBrandException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidBrandException} with the specified invalid brand.
     *
     * @param brand the invalid brand value
     */
    public InvalidBrandException(String brand) {
        super(String.format("Invalid brand: %s", brand));
    }
}
