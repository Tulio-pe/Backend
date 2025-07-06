package com.acme.tallerazo.workShopManagment.domain.model.entities;

import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.WorkshopServices;
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
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private WorkshopServices name;
    public Service(WorkshopServices name) {
        this.name= name;
    }
    public String getStringName(){
        return name.name() ;
    }
    public static Service getDefaultService(){
        return new Service(WorkshopServices.OIL_CHANGE);
    }
    public static Service ToServiceFromName(String name){
      return new Service(WorkshopServices.valueOf(name));
    }
    public static List<Service>ValidateServiceSet(List<Service> services){
    if(services==null||services.isEmpty()){
        return List.of(getDefaultService());
    }
    return services;
    }
}
