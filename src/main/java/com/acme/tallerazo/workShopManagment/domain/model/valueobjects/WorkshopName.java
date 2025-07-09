package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import com.acme.tallerazo.workShopManagment.domain.exceptions.WorkshopNameInvalidException;
import jakarta.persistence.Embeddable;

@Embeddable
public record WorkshopName(String workshopName) {
    public WorkshopName(){this(null);}
    public WorkshopName{
     if(workshopName==null||workshopName.isBlank()){
         throw new WorkshopNameInvalidException(workshopName);
     }
    }
}
