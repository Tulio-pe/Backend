package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopDescription(String description) {
    public WorkshopDescription(){this(null);}
    public WorkshopDescription {
        if(description == null||description.isBlank()){throw new IllegalArgumentException("Description cannot be null or blank");}
    }
}
