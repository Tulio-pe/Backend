package com.acme.tallerazo.RepairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.RepairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.RepairManagement.interfaces.rest.resource.CarResource;

public class CarResourceFromEntityAssembler {
    public static CarResource toResourceFromEntity(Car car){
     return new CarResource(car.getPlate(),car.getModel(),car.getYear());

    }
}
