package com.acme.tallerazo.workShopManagment.domain.exceptions;

/**
 * Exception thrown when the name provided for a workshop is invalid.
 *
 * @summary
 * This exception is raised when a workshop name does not meet the expected format
 * or business rules defined in the domain.
 *
 * @see RuntimeException
 */
public class WorkshopNameInvalidException extends RuntimeException {
    /**
     * Constructs a new WorkshopNameInvalidException with a message indicating
     * the invalid name.
     *
     * @param name The invalid workshop name that caused the exception.
     */
    public WorkshopNameInvalidException(String name) {
        super(String.format("Name invalid %s", name));
    }
}
