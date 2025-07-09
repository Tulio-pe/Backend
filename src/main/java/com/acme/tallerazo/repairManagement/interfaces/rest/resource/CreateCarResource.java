package com.acme.tallerazo.repairManagement.interfaces.rest.resource;

public record CreateCarResource(String brand, String model, String licensePlate, String  fuelType, String year, Long workshopId) {
}
