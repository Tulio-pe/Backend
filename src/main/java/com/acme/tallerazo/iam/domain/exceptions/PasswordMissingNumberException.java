package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when the password does not contain at least one number.
 *
 * @summary
 * This exception is raised when the password lacks a numeric digit (0-9).
 */
public class PasswordMissingNumberException extends RuntimeException {
    public PasswordMissingNumberException() {
        super("Password must contain at least one number");
    }
}
