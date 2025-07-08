package com.acme.tallerazo.repairManagement.domain.exceptions;

/**
 * Thrown to indicate that the model field is blank.
 *
 * <p>This exception is used when the model value is null, empty, or contains only whitespace.</p>
 */
public class BlankModelException extends RuntimeException {

    /**
     * Constructs a new {@code BlankModelException} with the specified message.
     *
     * @param model the detail message describing the blank model field
     */
    public BlankModelException(String model) {
        super(String.format("Blank model: %s", model));
    }

}
