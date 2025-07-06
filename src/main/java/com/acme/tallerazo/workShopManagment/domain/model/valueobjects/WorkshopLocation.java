package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopLocation(String address, District districtId ) {
    public WorkshopLocation(){
        this(null,null);
    }
    public WorkshopLocation {
        if(address == null||address.isBlank() ){
            throw new IllegalArgumentException("Workshop address cannot be null or blank");
        }
        if(districtId == null){
            throw new IllegalArgumentException("District ID cannot be null");
        }
    }
}
