package com.acme.tallerazo.iam.domain.exceptions;

public class LastNameBlankException extends RuntimeException {
    public LastNameBlankException(String lastName) {
        super(
                String.format("Last name %s is blank", lastName)
        );
    }
}
