package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopPhone(String workshopPhone) {
    public WorkshopPhone(){this(null);}
    public  WorkshopPhone{
        if(workshopPhone == null||workshopPhone.isBlank()){
            throw new IllegalArgumentException("Workshop phone cannot be null or blank");
        }
    }
}
