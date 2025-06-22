package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopAddress(String address ) {
    public WorkshopAddress(){this(null);}
    public WorkshopAddress {
        if(address == null||address.isBlank()){
            throw new IllegalArgumentException("Workshop address cannot be null or blank");
        }
    }
}
