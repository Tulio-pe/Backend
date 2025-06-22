package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record RepairId(String id) {
    public RepairId(){this(UUID.randomUUID().toString());}
    public RepairId{
        if(id==null||id.isBlank()){
            throw new IllegalArgumentException("Repair ID cannot be null or blank");
        }
    }
}
