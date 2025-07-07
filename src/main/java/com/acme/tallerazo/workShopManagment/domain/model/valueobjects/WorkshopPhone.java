package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import com.acme.tallerazo.workShopManagment.domain.exceptions.WorkshopPhoneInvalidException;
import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopPhone(String workshopPhone) {
    public WorkshopPhone(){this(null);}
    public  WorkshopPhone{
        if(workshopPhone == null||workshopPhone.isBlank()){
            throw new WorkshopPhoneInvalidException(workshopPhone);}
    }
}
