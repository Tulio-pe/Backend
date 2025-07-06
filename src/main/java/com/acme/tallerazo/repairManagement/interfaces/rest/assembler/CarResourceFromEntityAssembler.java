package com.acme.tallerazo.repairManagement.interfaces.rest.assembler;

import com.acme.tallerazo.repairManagement.domain.model.aggregates.Car;
import com.acme.tallerazo.repairManagement.interfaces.rest.resource.CarResource;

public class CarResourceFromEntityAssembler {
    public static CarResource toResourceFromEntity(Car car){
     return new CarResource(car.getPlate(),car.getModel(),car.getYear());

    }
}
