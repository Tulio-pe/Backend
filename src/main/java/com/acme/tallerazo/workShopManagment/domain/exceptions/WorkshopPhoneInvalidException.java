package com.acme.tallerazo.workShopManagment.domain.exceptions;

public class WorkshopPhoneInvalidException extends RuntimeException {
    /**
     * Exception thrown when the phone number provided for a workshop is invalid.
     *
     * @summary
     * This exception is raised when a phone number assigned to a workshop does not comply
     * with the expected format or business rules defined in the domain.
     *
     * @see RuntimeException
     */
    public WorkshopPhoneInvalidException(String Phone)
    {
        /*
         * Constructs a new WorkshopPhoneInvalidException with a message indicating
         * the invalid phone number.
         *
         * @param phone The invalid phone number that caused the exception.
         */
        super(String.format("Phone invalid %s",Phone));
    }
}
