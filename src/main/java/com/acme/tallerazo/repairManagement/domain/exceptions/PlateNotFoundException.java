package com.acme.tallerazo.repairManagement.domain.exceptions;

public class PlateNotFoundException extends RuntimeException {
    public PlateNotFoundException(String plate) {
        super(String.format("Plate %s not found", plate));
    }
}
