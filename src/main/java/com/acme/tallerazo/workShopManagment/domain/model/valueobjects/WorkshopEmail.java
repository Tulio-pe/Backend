package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
@Embeddable
public record WorkshopEmail(@Email String value) {
    public WorkshopEmail(){this(null);}
    public WorkshopEmail{
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Workshop email cannot be null or blank");
        }
    }
}
