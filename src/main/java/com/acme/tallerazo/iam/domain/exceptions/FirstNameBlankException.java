package com.acme.tallerazo.iam.domain.exceptions;

public class FirstNameBlankException extends RuntimeException {
    public FirstNameBlankException(String firstName) {
        super(
                String.format("First name %s is blank", firstName)
        );
    }
}
