package com.acme.tallerazo.iam.domain.model.valueobjects;

import com.acme.tallerazo.iam.domain.exceptions.*;
import jakarta.persistence.Embeddable;

/**
 * Value object representing a secure user password.
 */
@Embeddable
public record Password(String password) {
    public Password {
        if (password == null || password.isBlank()) {
            throw new PasswordBlankException();
        }
        if (password.length() < 8) {
            throw new PasswordTooShortException();
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new PasswordMissingUppercaseException();
        }
        if (!password.matches(".*[a-z].*")) {
            throw new PasswordMissingLowercaseException();
        }
        if (!password.matches(".*\\d.*")) {
            throw new PasswordMissingNumberException();
        }
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            throw new PasswordMissingSpecialCharacterException();
        }
    }
}
