package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ManagerId(Long managerId) {
    /**
     * Default constructor
     */
    public ManagerId() {
        this(null);
    }
}
