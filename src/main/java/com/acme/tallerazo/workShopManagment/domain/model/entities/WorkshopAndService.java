package com.acme.tallerazo.workShopManagment.domain.model.entities;

import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkServices;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name="services")
public class WorkshopAndService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private WorkServices name;
    public WorkshopAndService(WorkServices name) {
        this.name= name;

    }
    public String getStringName(){
        return name.name() ;
    }
    public static WorkshopAndService getDefaultService(){
        return new WorkshopAndService(WorkServices.OIL_CHANGE);
    }
    public static WorkshopAndService ToServiceFromName(String name){
      return new WorkshopAndService(WorkServices.valueOf(name));
    }
    public static List<WorkshopAndService>ValidateServiceSet(List<WorkshopAndService> services){
    if(services==null||services.isEmpty()){
        return List.of(getDefaultService());
    }
    return services;
    }
}
