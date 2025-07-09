package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the provided year is invalid.
 *
 * <p>This may happen if the year is null, blank, or outside an acceptable range (e.g., not a valid 4-digit year).</p>
 */
public class InvalidYearException extends RuntimeException {

    /**
     * Constructs a new {@code InvalidYearException} with the specified invalid year.
     *
     * @param year the invalid year value
     */
    public InvalidYearException(String year) {
        super(String.format("Invalid year: %s", year));
    }
}
