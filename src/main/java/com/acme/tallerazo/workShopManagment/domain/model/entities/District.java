package com.acme.tallerazo.workShopManagment.domain.model.entities;

import com.acme.tallerazo.workShopManagment.domain.model.aggregates.Workshop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "districts")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "district_name", nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_district_province"))
    private Province province;

    @OneToMany(
            mappedBy = "workshopLocation.district",
            fetch    = FetchType.LAZY,
            cascade  = CascadeType.ALL
    )
    private Set<Workshop> workshops = new HashSet<>();

    public District(String name, Province province) {
        this.name = name;
        this.province = province;
    }

    public District(String name, Province province, Set<Workshop> workshops) {
        this.name = name;
        this.province = province;
        this.workshops = workshops;
    }


}
