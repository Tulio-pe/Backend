package com.acme.tallerazo.repairManagement.domain.model.commands;

public record CreateCarCommand(String brand,String model,String licensePlate,String  fuelType,String year, Long workshopId) {

}
