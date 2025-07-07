package com.acme.tallerazo.workShopManagment.domain.model.entities;

import com.acme.tallerazo.workShopManagment.domain.model.valueobjects.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name="schedule_entry")
@Getter
public class ScheduleEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;
    @Column(nullable = false)
    private String startTime;
    @Column(nullable = false)
    private String endTime;
}
