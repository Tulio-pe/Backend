package com.acme.tallerazo.workShopManagment.domain.exceptions;

/**
 * Exception thrown when the email provided for a workshop is invalid.
 *
 * @summary
 * This exception is raised when the email assigned to a workshop does not comply
 * with the expected format or violates business rules defined in the domain.
 *
 * @see RuntimeException
 */
public class WorkshopEmailInvalidException extends RuntimeException {
    /**
     * Constructs a new WorkshopEmailInvalidException with a message indicating
     * the invalid email.
     *
     * @param email The invalid workshop email that caused the exception.
     */
    public WorkshopEmailInvalidException(String email) {
        super(String.format("Email invalid %s", email));
    }
}
