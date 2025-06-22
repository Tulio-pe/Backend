package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Year(String year) {
    public Year(){this(null);}
    public Year{
        if(year==null||year.isBlank()){
            throw new IllegalArgumentException("Year cannot be null or blank");
        }
    }
}
