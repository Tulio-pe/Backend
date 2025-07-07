package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when the password does not contain at least one lowercase letter.
 *
 * @summary
 * This exception is raised when the password lacks a lowercase character (a-z).
 */
public class PasswordMissingLowercaseException extends RuntimeException {
    public PasswordMissingLowercaseException() {
        super("Password must contain at least one lowercase letter");
    }
}
