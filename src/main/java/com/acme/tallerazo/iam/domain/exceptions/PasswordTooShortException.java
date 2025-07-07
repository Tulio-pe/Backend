package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when the password is shorter than the minimum allowed length.
 *
 * @summary
 * This exception is raised when the password has fewer than 8 characters.
 */
public class PasswordTooShortException extends RuntimeException {
    public PasswordTooShortException() {
        super("Password must be at least 8 characters long");
    }
}
