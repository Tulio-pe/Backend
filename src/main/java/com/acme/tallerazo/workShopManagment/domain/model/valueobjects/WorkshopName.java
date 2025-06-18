package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopName(String workshopName) {
    public WorkshopName(){this(null);}
    public WorkshopName{
     if(workshopName==null||workshopName.isBlank()){
         throw new IllegalArgumentException("Work shop name cannot be null or empty");
     }
    }
}
