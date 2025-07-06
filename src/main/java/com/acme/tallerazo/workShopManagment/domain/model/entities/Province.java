package com.acme.tallerazo.workShopManagment.domain.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "provinces")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "province_name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_province_region"))
    private Region region;

    public Province(String name, Region region) {
        this.name = name;
        this.region = region;
    }
}
