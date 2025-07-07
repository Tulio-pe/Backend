package com.acme.tallerazo.repairManagement.domain.exceptions;

public class PlateAlreadyExistsException extends RuntimeException {
    public PlateAlreadyExistsException(String Plate) {
        super(String.format("Plate %s already exists", Plate));
    }
}
