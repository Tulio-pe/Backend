package com.acme.tallerazo.repairManagement.domain.model.aggregates;

import com.acme.tallerazo.repairManagement.domain.model.commands.CreateRepairCommand;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.Details;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.RepairId;
import com.acme.tallerazo.repairManagement.domain.model.valueobjects.RepairOrderStatus;
import com.acme.tallerazo.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class RepairOrder extends AuditableAbstractAggregateRoot<RepairOrder> {
    @Embedded
    @AttributeOverride(name="id", column = @Column(name="order_id"))
private RepairId repairId;
    @Embedded
    private Details details;
    @Getter
    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RepairOrderStatus status;

    public String getStatus(){return this.status.name().toLowerCase();}

   public RepairOrder(){}
    public RepairOrder(Car car,CreateRepairCommand command,RepairId repairId){
         this.car=car;
         this.status= RepairOrderStatus.To_be_reviewed;
         this.repairId=new RepairId((repairId.id()));
         this.details= new Details(command.details());
    }
    public String getRepairId(){return this.repairId.id();}
    public String getPlate(){ return car.getPlate();}
}
