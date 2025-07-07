package com.acme.tallerazo.workShopManagment.domain.exceptions;

/**
 * Exception thrown when the description provided for a workshop is invalid.
 *
 * @summary
 * This exception is raised when a workshop description does not meet the expected
 * format or violates domain-specific business rules.
 *
 * @see RuntimeException
 */
public class WorkshopDescriptionInvalidException extends RuntimeException {
    /**
     * Constructs a new WorkshopDescriptionInvalidException with a message indicating
     * the invalid description.
     *
     * @param description The invalid workshop description that caused the exception.
     */
    public WorkshopDescriptionInvalidException(String description) {
        super(String.format("Description invalid %s", description));
    }
}
