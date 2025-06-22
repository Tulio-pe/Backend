package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Plate(String plate) {
    public Plate(){this(null);}
public Plate{
    if(plate==null||plate.isBlank()){
        throw new IllegalArgumentException("plate is null or empty");
    }
}
}
