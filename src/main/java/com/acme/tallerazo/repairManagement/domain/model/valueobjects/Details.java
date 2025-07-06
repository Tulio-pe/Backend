package com.acme.tallerazo.repairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Details(String details) {
    public Details(){this(null);}
    public Details{
     if(details==null||details.isBlank()){
         throw new IllegalArgumentException("Details cannot be null or blank");
     }
    }
}
