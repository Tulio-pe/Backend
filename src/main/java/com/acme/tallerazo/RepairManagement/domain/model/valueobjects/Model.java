package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Model(String model) {

    public Model(){this(null);}
    public Model{
        if(model==null||model.isBlank()){
            throw new IllegalStateException("Model is empty");
        }
    }
}
