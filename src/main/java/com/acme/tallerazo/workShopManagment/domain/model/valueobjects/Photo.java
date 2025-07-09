package com.acme.tallerazo.workShopManagment.domain.model.valueobjects;

import com.acme.tallerazo.workShopManagment.domain.exceptions.PhotoInvalidException;
import jakarta.persistence.Embeddable;

@Embeddable
public record Photo(String photo) {
    public Photo() {this(null);}
    public Photo{
        if(photo==null||photo.isBlank()){
            throw new PhotoInvalidException(photo);
        }
    }
}
