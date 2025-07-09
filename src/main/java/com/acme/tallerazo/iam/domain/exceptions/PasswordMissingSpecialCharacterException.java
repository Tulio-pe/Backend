package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when the password does not contain at least one special character.
 *
 * @summary
 * This exception is raised when the password lacks a non-alphanumeric character (e.g., !@#$%^&*).
 */
public class PasswordMissingSpecialCharacterException extends RuntimeException {
    public PasswordMissingSpecialCharacterException() {
        super("Password must contain at least one special character");
    }
}
