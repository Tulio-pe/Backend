package com.acme.tallerazo.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PersonName(String firstName, String lastName) {
    public PersonName(){this(null, null);}
    public PersonName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");}
            if (lastName == null || lastName.isBlank()) {
                throw new IllegalArgumentException("Last name cannot be null or blank");
            }

    }

   public  String getFullName() {return "%s,%s".formatted(firstName,lastName);}

}
