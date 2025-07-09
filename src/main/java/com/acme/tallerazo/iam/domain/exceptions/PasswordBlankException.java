package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when a password is null or blank.
 *
 * @summary
 * This exception is raised when the password provided is either null or consists only of whitespace.
 */
public class PasswordBlankException extends RuntimeException {
    public PasswordBlankException() {
        super("Password cannot be null or blank");
    }
}
