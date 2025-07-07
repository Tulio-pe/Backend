package com.acme.tallerazo.iam.domain.exceptions;

/**
 * Exception thrown when the password does not contain at least one uppercase letter.
 *
 * @summary
 * This exception is raised when the password lacks an uppercase character (A-Z).
 */
public class PasswordMissingUppercaseException extends RuntimeException {
    public PasswordMissingUppercaseException() {
        super("Password must contain at least one uppercase letter");
    }
}
