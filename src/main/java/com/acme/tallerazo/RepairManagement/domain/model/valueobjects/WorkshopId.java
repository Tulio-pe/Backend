package com.acme.tallerazo.RepairManagement.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopId(Long workshopId) {
}
