package com.acme.tallerazo.iam.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
@Embeddable
public record EmailAddress(@Email String value) {
    public EmailAddress(){this(null);}
 public EmailAddress{
     if(value==null||value.isBlank()){
         throw new IllegalArgumentException("Email address cannot be null or blank");
     }
 }
}
