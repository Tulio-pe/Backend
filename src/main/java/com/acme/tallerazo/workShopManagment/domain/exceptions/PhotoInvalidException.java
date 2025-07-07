package com.acme.tallerazo.workShopManagment.domain.exceptions;

/**
 * Exception thrown when the photo provided for a workshop is invalid.
 *
 * @summary
 * This exception is raised when a photo associated with a workshop does not meet
 * the expected format, size, or business rules defined in the domain layer.
 *
 * @see RuntimeException
 */
public class PhotoInvalidException extends RuntimeException {
    /**
     * Constructs a new PhotoInvalidException with a message indicating
     * the invalid photo input.
     *
     * @param photo The invalid photo value that caused the exception.
     */
    public PhotoInvalidException(String photo) {
        super(String.format("Photo invalid %s", photo));
    }
}
