package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import com.acme.tallerazo.workShopManagment.domain.model.entities.District;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class WorkshopLocation {

    @Column(name = "workshop_address", nullable = false)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "district_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_workshop_district")
    )
    private District district;


    public WorkshopLocation(String address, District district) {
        this.address = address;
        this.district = district;
    }
}