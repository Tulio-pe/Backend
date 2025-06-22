package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record FuelType(String fuelType) {
    public FuelType(){this(null);}
    public FuelType{
        if(fuelType==null||fuelType.isBlank()){
            throw new IllegalArgumentException("Fuel Type cannot be null or blank");
        }
    }
}
