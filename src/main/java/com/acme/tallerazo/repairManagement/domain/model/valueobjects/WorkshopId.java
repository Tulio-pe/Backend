package com.acme.tallerazo.repairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopId(Long workshopId) {
    /**
     * Default constructor
     */
    public WorkshopId() {
        this(null);
    }
}
